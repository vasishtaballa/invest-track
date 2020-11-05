package dev.vasishta.invest.track.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBUtils {
    public static Object resultSetToPojo(ResultSet rs, Class pojo) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
            map.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
        return objectMapper.convertValue(map, pojo);
    }
}
