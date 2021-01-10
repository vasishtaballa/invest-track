package dev.vasishta.invest.track.dao.impl;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Deposit;
import dev.vasishta.invest.track.bean.QueryUpdate;
import dev.vasishta.invest.track.config.DBConfig;
import dev.vasishta.invest.track.constant.MessageType;
import dev.vasishta.invest.track.constant.QueryType;
import dev.vasishta.invest.track.constant.ResponseMessages;
import dev.vasishta.invest.track.constant.Tables;
import dev.vasishta.invest.track.dao.DepositDAO;
import dev.vasishta.invest.track.dao.QueryUpdateDAO;
import dev.vasishta.invest.track.util.DBUtils;
import dev.vasishta.invest.track.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DepositDAOImpl extends QueryUpdateDAO implements DepositDAO {

    @Autowired
    DBConfig dbConfig;

    @Override
    public void addDeposit(Deposit deposit, BaseBean baseBean) {
        try (Connection con = dbConfig.getDataSource().getConnection();
             PreparedStatement statement = con.prepareStatement(ADD_DEPOSIT)) {
            statement.setDate(1, deposit.getDate());
            statement.setDouble(2, deposit.getAmount());
            updateQueryTable(new QueryUpdate(Tables.DEPOSITS.name(), QueryType.INSERT.name(), DBUtils.getQuery(statement)));
            statement.execute();
            baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.INSERT_DEPOSIT_SUCCESS, MessageType.SUCCESS));
        } catch (SQLException ex) {
            ex.printStackTrace();
            baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.INSERT_DEPOSIT_FAIL, MessageType.FAIL));
        }
    }

    @Override
    public void getDeposits(BaseBean baseBean) {
        try (Connection con = dbConfig.getDataSource().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_DEPOSITS)) {
            ResultSet rs = statement.executeQuery();
            List<Deposit> deposits = new ArrayList<>();
            while (rs.next()) {
                Deposit deposit = (Deposit) DBUtils.resultSetToPojo(rs, Deposit.class);
                deposits.add(deposit);
            }
            baseBean.setResponse(deposits);
        } catch (SQLException ex) {
            ex.printStackTrace();
            baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.GET_DEPOSIT_FAIL, MessageType.FAIL));
        }
    }
}
