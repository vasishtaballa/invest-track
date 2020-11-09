package dev.vasishta.invest.track.dao;

public interface Queries {
    static String GET_PORTFOLIO = "SELECT tr.id, tr.equitySymbol, eq.name 'equityName', eq.mcSymbol, DATE_FORMAT(tr.date, \"%Y-%m-%d\") 'date', tr.exchange, tr.mode, tr.price, tr.qty, tr.target, tr.brokerage, tr.gross, tr.net FROM trades tr JOIN equities eq WHERE equitySymbol IN (SELECT equitySymbol FROM depository WHERE count > 0) AND tr.equitySymbol = eq.symbol;";
    static String ADD_TRADE = "INSERT INTO `sample`.`buy_trades` ( `equitySymbol`, `date`, `exchange`, `price`, `qty`, `target`, `brokerage`, `pbt`, `brokerageAmount`, `taxes`, `net`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    // static String GET_BALANCE_TRADE = "SELECT COUNT(*) AS count FROM `sample`.`balance_trades` WHERE equitySymbol = ? AND exchange = ?;";
    static String INSERT_BALANCE_TRADE = "INSERT INTO `sample`.`balance_trades` (`buyTradeId`, `equitySymbol`, `exchange`, `qty`) VALUES ( ?, ?, ?, ?);";
}
