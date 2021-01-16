package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.util.Date;

@Data
public class StatementItem {
    private Date date;
    private String type;
    private String equityName;
    private int qty;
    private double price;
    private double net;
}
