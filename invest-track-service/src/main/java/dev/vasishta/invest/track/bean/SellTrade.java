package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.sql.Date;

@Data
public class SellTrade {
    private int id;
    private BuyTrade buyTrade;
    private Date date;
    private double price;
    private int qty;
    private double margin;
    private double brokerage;
    private double pbt;
    private double brokerageAmount;
    private double taxes;
    private double net;
}
