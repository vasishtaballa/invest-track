package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.util.List;

@Data
public class Stock {
    private String equitySymbol;
    private String equityName;
    private String mcSymbol;
    private List<Trade> trades;
    private double avgPrice;
    private int qty;
    private double currentPrice;
    private double netPrice;
    private double netValue;
    private double margin;
    private double netPL;
}
