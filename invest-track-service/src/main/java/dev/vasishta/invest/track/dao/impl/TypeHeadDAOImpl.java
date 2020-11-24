package dev.vasishta.invest.track.dao.impl;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.TypeHead;
import dev.vasishta.invest.track.config.DBConfig;
import dev.vasishta.invest.track.constant.MessageType;
import dev.vasishta.invest.track.constant.ResponseMessages;
import dev.vasishta.invest.track.dao.Queries;
import dev.vasishta.invest.track.dao.TypeHeadDAO;
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
public class TypeHeadDAOImpl implements TypeHeadDAO, Queries {

    @Autowired
    private DBConfig dbConfig;

    @Override
    public void getTypeHeads(BaseBean baseBean) {
        TypeHead typeHead = new TypeHead();
        typeHead.setEquities(getTypeHeadsFor(TH_EQUITIES, baseBean));
        typeHead.setSectors(getTypeHeadsFor(TH_SECTORS, baseBean));
        typeHead.setEquitySymbols(getTypeHeadsFor(TH_EQUITY_SYMBOLS, baseBean));
        typeHead.setExchanges(getTypeHeadsFor(TH_EXCHANGES, baseBean));
        typeHead.setMcEquitySymbols(getTypeHeadsFor(TH_MC_EQUITY_SYMBOLS, baseBean));
        baseBean.setResponse(typeHead);
    }

    private List<String> getTypeHeadsFor(String query, BaseBean baseBean) {
        List<String> typeHeads = null;
        try (Connection con = dbConfig.getDataSource().getConnection();
             PreparedStatement statement = con.prepareStatement(query);) {
            ResultSet rs = statement.executeQuery();
            if (rs != null)
                typeHeads = new ArrayList<>();
            while (rs.next()) {
                typeHeads.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            baseBean.getMessages().add(ResponseUtil.getMessageObj(ResponseMessages.GET_TH_FAIL + query, MessageType.FAIL));
        }
        return typeHeads;
    }

}
