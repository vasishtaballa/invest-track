package dev.vasishta.invest.track.dao;

import dev.vasishta.invest.track.bean.QueryUpdate;
import dev.vasishta.invest.track.config.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class QueryUpdateDAO implements Queries {
    @Autowired
    private DBConfig dbConfig;

    protected void updateQueryTable(QueryUpdate queryUpdate) {
        Connection con = null;
        try {
            con = dbConfig.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement(ADD_QUERY_UPDATE);
            int i = 1;
            statement.setString(i++, queryUpdate.getTable());
            statement.setString(i++, queryUpdate.getType());
            statement.setString(i++, queryUpdate.getQuery());
            System.out.println(statement.toString());
            statement.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
