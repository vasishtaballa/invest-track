package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Trade {
    private int id;
    private String equitySymbol;
    private String equityName;
    private String mcSymbol;
    private Date date;
    private String exchange;
    private double price;
    private int buyQty;
    private int balQty;
    private double target;
    private double brokerage;
    private double pbt;
    private double brokerageAmount;
    private double taxes;
    private double net;
}
