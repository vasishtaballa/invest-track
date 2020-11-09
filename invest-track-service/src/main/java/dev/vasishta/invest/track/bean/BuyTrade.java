package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.sql.Date;

@Data
public class BuyTrade {
    private int id;
    private String equitySymbol;
    private Date date;
    private String exchange;
    private double price;
    private int qty;
    private double target;
    private double brokerage;
    private double pbt;
    private double brokerageAmount;
    private double taxes;
    private double net;
}
