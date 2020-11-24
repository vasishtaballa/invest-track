package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.util.List;

@Data
public class TypeHead {
    private List<String> equities;
    private List<String> sectors;
    private List<String> equitySymbols;
    private List<String> mcEquitySymbols;
    private List<String> exchanges;
}
