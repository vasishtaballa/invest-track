package dev.vasishta.invest.track.dao.impl;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.StatementItem;
import dev.vasishta.invest.track.config.DBConfig;
import dev.vasishta.invest.track.constant.MessageType;
import dev.vasishta.invest.track.constant.ResponseMessages;
import dev.vasishta.invest.track.dao.Queries;
import dev.vasishta.invest.track.dao.StatementDAO;
import dev.vasishta.invest.track.util.DBUtils;
import dev.vasishta.invest.track.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class StatementDAOImpl implements StatementDAO, Queries {

    @Autowired
    private DBConfig dbConfig;

    @Override
    public List<StatementItem> getStatement(BaseBean baseBean) {
        List<StatementItem> buyStatementList = new ArrayList<>();
        List<StatementItem> sellStatementList = new ArrayList<>();
        try (Connection con = dbConfig.getDataSource().getConnection();
             PreparedStatement buyStatement = con.prepareStatement(GET_BUY_STATEMENT);
             PreparedStatement sellStatement = con.prepareStatement(GET_SELL_STATEMENT);) {
            ResultSet buyRS = buyStatement.executeQuery();
            ResultSet sellRS = sellStatement.executeQuery();
            while (buyRS.next()) {
                buyStatementList.add((StatementItem) DBUtils.resultSetToPojo(buyRS, StatementItem.class));
            }
            while (sellRS.next()) {
                sellStatementList.add((StatementItem) DBUtils.resultSetToPojo(sellRS, StatementItem.class));
            }
            return generateStatement(buyStatementList, sellStatementList);
        } catch (SQLException ex) {
            ex.printStackTrace();
            baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.GET_STATEMENT_FAIL, MessageType.FAIL));
        }
        return null;
    }

    private List<StatementItem> generateStatement(List<StatementItem> buyStatementList, List<StatementItem> sellStatementList) {
        List<StatementItem> completeStatement = new ArrayList<>();
        completeStatement.addAll(buyStatementList);
        completeStatement.addAll(sellStatementList);
        Collections.sort(completeStatement, new Comparator<StatementItem>() {
            @Override
            public int compare(StatementItem o1, StatementItem o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return completeStatement;
    }
}
