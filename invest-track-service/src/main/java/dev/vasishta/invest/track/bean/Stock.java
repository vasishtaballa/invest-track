package dev.vasishta.invest.track.bean;

import lombok.Data;

@Data
public class Stock {
    private int id;
    private String equitySymbol;
    private String exchange;
    private int count;
}
