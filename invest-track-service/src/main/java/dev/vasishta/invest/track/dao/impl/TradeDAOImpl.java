package dev.vasishta.invest.track.dao.impl;

import dev.vasishta.invest.track.bean.*;
import dev.vasishta.invest.track.config.DBConfig;
import dev.vasishta.invest.track.constant.MessageType;
import dev.vasishta.invest.track.constant.QueryType;
import dev.vasishta.invest.track.constant.ResponseMessages;
import dev.vasishta.invest.track.constant.Tables;
import dev.vasishta.invest.track.dao.QueryUpdateDAO;
import dev.vasishta.invest.track.dao.TradeDAO;
import dev.vasishta.invest.track.util.DBUtils;
import dev.vasishta.invest.track.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class TradeDAOImpl extends QueryUpdateDAO implements TradeDAO {

    @Autowired
    private DBConfig dbConfig;

    @Override
    public void addTrade(BuyTrade buyTrade, BaseBean baseBean) {
        Connection con = null;
        try {
            con = dbConfig.getDataSource().getConnection();
            con.setAutoCommit(false);
            updateEquities(buyTrade, con);
            PreparedStatement statement = con.prepareStatement(ADD_TRADE, Statement.RETURN_GENERATED_KEYS);
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
            System.out.println(DBUtils.getQuery(statement));
            updateQueryTable(new QueryUpdate(Tables.BUY_TRADES.name(), QueryType.INSERT.name(), DBUtils.getQuery(statement)));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int tradeId = rs.getInt(1);
                baseBean.getMessages().add(updateBalanceTrades(con, buyTrade, tradeId));
                con.commit();
                statement.close();
            }
        } catch (SQLException ex) {
            try {
                ex.printStackTrace();
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.ADD_TRADE_FAIL, MessageType.FAIL));
        }
        baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.ADD_TRADE_SUCCESS, MessageType.SUCCESS));
    }

    @Override
    public void sellEquity(SellTrade sellTrade, BaseBean baseBean) {
        Connection con = null;
        try {
            con = dbConfig.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(GET_BALANCE_TRADE);
            statement.setInt(1, sellTrade.getBuyTrade().getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int balance = rs.getInt(1);
                if (balance < sellTrade.getQty()) {
                    baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.UPDATE_BALANCE_TRADES_INSUFFICIENT, MessageType.FAIL));
                    return;
                } else if (balance > sellTrade.getQty())
                    baseBean.getMessages().add(updateBalanceTradesQty(sellTrade, con, balance));
                else
                    baseBean.getMessages().add(deleteFromBalanceTrades(sellTrade, con));
                updateSellTrades(sellTrade, con, baseBean);
                con.commit();
            } else
                baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.UPDATE_BALANCE_TRADES_FAIL, MessageType.FAIL));
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.UPDATE_BALANCE_TRADES_FAIL, MessageType.FAIL));
        }
    }

    private void updateSellTrades(SellTrade sellTrade, Connection con, BaseBean baseBean) throws SQLException {
        try (PreparedStatement statement = con.prepareStatement(INSERT_SELL_TRADES)) {
            int i = 1;
            statement.setInt(i++, sellTrade.getBuyTrade().getId());
            statement.setDate(i++, sellTrade.getDate());
            statement.setDouble(i++, sellTrade.getPrice());
            statement.setInt(i++, sellTrade.getQty());
            statement.setDouble(i++, sellTrade.getMargin());
            statement.setDouble(i++, sellTrade.getBrokerage());
            statement.setDouble(i++, sellTrade.getPbt());
            statement.setDouble(i++, sellTrade.getBrokerageAmount());
            statement.setDouble(i++, sellTrade.getTaxes());
            statement.setDouble(i++, sellTrade.getNet());
            updateQueryTable(new QueryUpdate(Tables.SELL_TRADES.name(), QueryType.INSERT.name(), DBUtils.getQuery(statement)));
            statement.execute();
            baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.INSERT_SELL_TRADES_SUCCESS, MessageType.SUCCESS));
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private Message deleteFromBalanceTrades(SellTrade sellTrade, Connection con) {
        try (PreparedStatement statement = con.prepareStatement(DELETE_BALANCE_TRADE)) {
            statement.setInt(1, sellTrade.getBuyTrade().getId());
            updateQueryTable(new QueryUpdate(Tables.BALANCE_TRADES.name(), QueryType.DELETE.name(), DBUtils.getQuery(statement)));
            statement.execute();
            return ResponseUtil.getMessageObj(ResponseMessages.DELETE_BALANCE_TRADES_SUCCESS, MessageType.SUCCESS);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseUtil.getMessageObj(ResponseMessages.DELETE_BALANCE_TRADES_FAIL, MessageType.FAIL);
        }
    }

    private Message updateBalanceTradesQty(SellTrade sellTrade, Connection con, int balance) {
        try (PreparedStatement statement = con.prepareStatement(UPDATE_BALANCE_TRADE)) {
            statement.setInt(1, balance - sellTrade.getQty());
            statement.setInt(2, sellTrade.getBuyTrade().getId());
            updateQueryTable(new QueryUpdate(Tables.BALANCE_TRADES.name(), QueryType.UPDATE.name(), DBUtils.getQuery(statement)));
            statement.execute();
            return ResponseUtil.getMessageObj(ResponseMessages.UPDATE_BALANCE_TRADES_SUCCESS, MessageType.SUCCESS);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseUtil.getMessageObj(ResponseMessages.UPDATE_BALANCE_TRADES_FAIL, MessageType.FAIL);
        }
    }

    private void updateEquities(BuyTrade buyTrade, Connection con) throws SQLException {
        try (PreparedStatement statement = con.prepareStatement(GET_EQUITY)) {
            statement.setString(1, buyTrade.getEquitySymbol());
            statement.setString(2, buyTrade.getMcSymbol());
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                try (PreparedStatement addEquityStatement = con.prepareStatement(ADD_EQUITY)) {
                    int i = 1;
                    addEquityStatement.setString(i++, buyTrade.getEquitySymbol());
                    addEquityStatement.setString(i++, buyTrade.getMcSymbol());
                    addEquityStatement.setString(i++, buyTrade.getName());
                    addEquityStatement.setString(i++, buyTrade.getSector());
                    updateQueryTable(new QueryUpdate(Tables.EQUITIES.name(), QueryType.INSERT.name(), DBUtils.getQuery(statement)));
                    addEquityStatement.execute();
                }
            }
        }
    }

    private Message updateBalanceTrades(Connection con, BuyTrade buyTrade, int tradeId) {
        try (PreparedStatement statement = con.prepareStatement(INSERT_BALANCE_TRADE)) {
            int i = 1;
            statement.setInt(i++, tradeId);
            statement.setString(i++, buyTrade.getEquitySymbol());
            statement.setString(i++, buyTrade.getExchange());
            statement.setInt(i++, buyTrade.getQty());
            System.out.println(DBUtils.getQuery(statement));
            updateQueryTable(new QueryUpdate(Tables.BALANCE_TRADES.name(), QueryType.INSERT.name(), DBUtils.getQuery(statement)));
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
