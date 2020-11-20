package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.sql.Date;

@Data
public class DashboardDetails {
    private int buyTradeId;
    private String equitySymbol;
    private String mcSymbol;
    private Date date;
    private String exchange;
    private double price;
    private int qty;
    private double target;
    private double pbt;
    private double brokerage;
    private double brokerageAmount;
    private double taxes;
    private double net;
    private int balQty;
}
