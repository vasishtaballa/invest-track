package dev.vasishta.invest.track.dao.impl;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.EquityPL;
import dev.vasishta.invest.track.config.DBConfig;
import dev.vasishta.invest.track.dao.GenericDAO;
import dev.vasishta.invest.track.dao.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GenericDAOImpl implements GenericDAO, Queries {
    @Autowired
    private DBConfig dbConfig;

    @Override
    public void getEquityPL(BaseBean baseBean) {
        List<EquityPL> equityPLList = new ArrayList<>();
        Map<String, EquityPL> netPLMap = new HashMap<>();
        try (Connection con = dbConfig.getDataSource().getConnection();
             PreparedStatement statement = con.prepareStatement(TOTAL_PROFIT_MARGIN_EQ_WISE)) {
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String equitySymbol = resultSet.getString(1);
                String equityName = resultSet.getString(2);
                int buyQty = resultSet.getInt(3);
                int sellQty = resultSet.getInt(4);
                double buyNet = resultSet.getDouble(5);
                double sellNet = resultSet.getDouble(6);
                double investment = getInvestment(buyQty, sellQty, buyNet, sellNet);
                double profit = getProfitForTrade(sellNet, investment);
                if (netPLMap.containsKey(equitySymbol)) {
                    netPLMap.get(equitySymbol).setNetPL(netPLMap.get(equitySymbol).getNetPL() + profit);
                } else {
                    EquityPL equityPL = new EquityPL(equitySymbol, equityName, profit);
                    netPLMap.put(equitySymbol, equityPL);
                }
            }
            netPLMap.forEach((s, equityPL) -> {
                equityPLList.add(equityPL);
            });
            baseBean.setResponse(equityPLList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private double getInvestment(int buyQty, int sellQty, double buyNet, double sellNet) {
        return ((buyNet / buyQty) * sellQty);
    }

    private double getProfitForTrade(double sellNet, double investment) {
        return sellNet - investment;
    }
}
