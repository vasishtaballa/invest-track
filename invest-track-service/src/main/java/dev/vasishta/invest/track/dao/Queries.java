package dev.vasishta.invest.track.dao;

public interface Queries {
    public static String GET_PORTFOLIO = "SELECT tr.id, tr.equitySymbol, eq.name 'equityName', tr.date, tr.exchange, tr.mode, tr.price, tr.qty, tr.target, tr.brokerage, tr.gross, tr.net FROM trades tr JOIN equities eq WHERE equitySymbol IN (SELECT equitySymbol FROM depository WHERE count > 0) AND tr.equitySymbol = eq.symbol";
}
