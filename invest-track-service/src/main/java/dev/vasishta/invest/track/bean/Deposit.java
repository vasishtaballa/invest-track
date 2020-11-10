package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.sql.Date;

@Data
public class Deposit {
    private double amount;
    private Date date;
}
