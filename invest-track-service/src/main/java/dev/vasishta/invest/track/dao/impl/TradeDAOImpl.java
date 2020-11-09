package dev.vasishta.invest.track.dao.impl;

import dev.vasishta.invest.track.bean.BuyTrade;
import dev.vasishta.invest.track.bean.Message;
import dev.vasishta.invest.track.config.DBConfig;
import dev.vasishta.invest.track.constant.MessageType;
import dev.vasishta.invest.track.constant.ResponseMessages;
import dev.vasishta.invest.track.dao.Queries;
import dev.vasishta.invest.track.dao.TradeDAO;
import dev.vasishta.invest.track.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class TradeDAOImpl implements TradeDAO, Queries {

    @Autowired
    private DBConfig dbConfig;

    @Override
    public Message addTrade(BuyTrade buyTrade) {
        try (Connection con = dbConfig.getDataSource().getConnection();
             PreparedStatement statement = con.prepareStatement(ADD_TRADE, Statement.RETURN_GENERATED_KEYS);) {
            con.setAutoCommit(false);
            int i = 1;
            statement.setString(i++, buyTrade.getEquitySymbol());
            statement.setDate(i++, buyTrade.getDate());
            statement.setString(i++, buyTrade.getExchange());
            statement.setDouble(i++, buyTrade.getPrice());
            statement.setInt(i++, buyTrade.getQty());
            statement.setDouble(i++, buyTrade.getTarget());
            statement.setDouble(i++, buyTrade.getBrokerage());
            statement.setDouble(i++, buyTrade.getPbt());
            statement.setDouble(i++, buyTrade.getBrokerageAmount());
            statement.setDouble(i++, buyTrade.getTaxes());
            statement.setDouble(i++, buyTrade.getNet());
            System.out.println(statement.toString());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int tradeId = rs.getInt(1);
                Message message = updateBalanceTrades(con, buyTrade, tradeId);
                con.commit();
                return message;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseUtil.getMessageObj(ResponseMessages.ADD_TRADE_FAIL, MessageType.FAIL);
        }
        return ResponseUtil.getMessageObj(ResponseMessages.ADD_TRADE_SUCCESS, MessageType.SUCCESS);
    }

    public Message updateBalanceTrades(Connection con, BuyTrade buyTrade, int tradeId) {
        try (PreparedStatement statement = con.prepareStatement(INSERT_BALANCE_TRADE)) {
            int i = 1;
            statement.setInt(i++, tradeId);
            statement.setString(i++, buyTrade.getEquitySymbol());
            statement.setString(i++, buyTrade.getExchange());
            statement.setInt(i++, buyTrade.getQty());
            System.out.println(statement.toString());
            statement.execute();
            return ResponseUtil.getMessageObj(ResponseMessages.INSERT_BALANCE_TRADES_SUCCESS, MessageType.SUCCESS);
        } catch (SQLException ex) {
            try {
                ex.printStackTrace();
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                return ResponseUtil.getMessageObj(ResponseMessages.INSERT_BALANCE_TRADES_FAIL, MessageType.FAIL);
            }
            return ResponseUtil.getMessageObj(ResponseMessages.INSERT_BALANCE_TRADES_FAIL, MessageType.FAIL);
        }
    }
}
