package dev.vasishta.invest.track.dao.impl;

import dev.vasishta.invest.track.bean.QueryUpdate;
import dev.vasishta.invest.track.config.DBConfig;
import dev.vasishta.invest.track.dao.CrashHandlingDAO;
import dev.vasishta.invest.track.dao.Queries;
import dev.vasishta.invest.track.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrashHandlingDAOImpl implements CrashHandlingDAO, Queries {
    @Autowired
    private DBConfig dbConfig;

    @Override
    public void handleCrash() {
        Connection con = null;
        try {
            con = dbConfig.getDataSource().getConnection();
            PreparedStatement statement = con.prepareStatement(GET_QUERY_UPDATES);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                QueryUpdate queryUpdate = (QueryUpdate) DBUtils.resultSetToPojo(rs, QueryUpdate.class);
                executeQueryUpdate(queryUpdate, con);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void executeQueryUpdate(QueryUpdate queryUpdate, Connection con) {
        String query = queryUpdate.getQuery();
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
