package dev.vasishta.invest.track.dao.impl;

import dev.vasishta.invest.track.bean.Trade;
import dev.vasishta.invest.track.config.DBConfig;
import dev.vasishta.invest.track.dao.PortfolioDAO;
import dev.vasishta.invest.track.dao.Queries;
import dev.vasishta.invest.track.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PortfolioDAOImpl implements PortfolioDAO, Queries {
    @Autowired
    private DBConfig dbConfig;

    @Override
    public List<Trade> getPortfolio() {
        List<Trade> list = null;
        try (Connection con = dbConfig.getDataSource().getConnection();
             PreparedStatement statement = con.prepareStatement(GET_PORTFOLIO);) {
            System.out.println(statement.toString());
            try (ResultSet rs = statement.executeQuery();) {
                list = new ArrayList<>();
                while (rs.next()) {
                    list.add((Trade) DBUtils.resultSetToPojo(rs, Trade.class));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
