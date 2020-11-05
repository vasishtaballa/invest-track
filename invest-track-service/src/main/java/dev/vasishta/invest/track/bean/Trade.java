package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Trade {
    private int id;
    private String equitySymbol;
    private String equityName;
    private Date date;
    private String exchange;
    private String mode;
    private double price;
    private int qty;
    private double target;
    private double brokerage;
    private double gross;
    private double net;
}
