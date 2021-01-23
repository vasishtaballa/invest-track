package dev.vasishta.invest.track.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EquityPL {
    private String equitySymbol;
    private String equityName;
    private double netPL;
}
