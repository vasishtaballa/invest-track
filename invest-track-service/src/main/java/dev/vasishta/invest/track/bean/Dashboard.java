package dev.vasishta.invest.track.bean;

import lombok.Data;

@Data
public class Dashboard {
    private double totalInvestments;
    private double totalWithdrew;
    private double totalTaxes;
    private double totalBrokerage;
    private double currentInvestments;
    private double currentTaxes;
    private double currentBrokerage;
    private double currentValueBT;
    private double netCurrentValue;
    private double currentPL;
    private double currentMargin;
    private double totalProfit;
    private double totalMargin;
    private double depositBal;
}
