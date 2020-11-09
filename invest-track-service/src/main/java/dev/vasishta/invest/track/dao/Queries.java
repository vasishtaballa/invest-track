package dev.vasishta.invest.track.dao;

public interface Queries {
    static String GET_PORTFOLIO = "SELECT buy.id AS id, buy.equitySymbol AS equitySymbol, eq.name AS equityName, eq.mcSymbol AS mcSymbol, buy.date AS date, buy.exchange AS exchange, buy.price AS price, buy.qty AS qty, buy.target AS target, buy.brokerage AS brokerage, buy.pbt AS pbt, buy.brokerageAmount AS brokerageAmount, buy.taxes AS taxes, buy.net AS net FROM balance_trades bal JOIN buy_trades buy ON bal.buyTradeId = buy.id JOIN equities eq ON eq.symbol = bal.equitySymbol;";
    static String ADD_TRADE = "INSERT INTO `sample`.`buy_trades` ( `equitySymbol`, `date`, `exchange`, `price`, `qty`, `target`, `brokerage`, `pbt`, `brokerageAmount`, `taxes`, `net`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    // static String GET_BALANCE_TRADE = "SELECT COUNT(*) AS count FROM `sample`.`balance_trades` WHERE equitySymbol = ? AND exchange = ?;";
    static String INSERT_BALANCE_TRADE = "INSERT INTO `sample`.`balance_trades` (`buyTradeId`, `equitySymbol`, `exchange`, `qty`) VALUES ( ?, ?, ?, ?);";
}
