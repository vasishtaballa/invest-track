package dev.vasishta.invest.track.bean;

import lombok.Data;

@Data
public class QueryUpdate {
    private int id;
    private String table;
    private String type;
    private String query;

    public QueryUpdate(String type, String table, String query) {
        this.table = table;
        this.type = type;
        this.query = query;
    }
}
