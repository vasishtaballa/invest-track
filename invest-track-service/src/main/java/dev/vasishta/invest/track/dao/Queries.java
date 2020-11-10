package dev.vasishta.invest.track.dao;

public interface Queries {
    String GET_PORTFOLIO = "SELECT buy.id AS id, buy.equitySymbol AS equitySymbol, eq.name AS equityName, eq.mcSymbol AS mcSymbol, buy.date AS date, buy.exchange AS exchange, buy.price AS price, bal.qty AS qty, buy.target AS target, buy.brokerage AS brokerage, buy.pbt AS pbt, buy.brokerageAmount AS brokerageAmount, buy.taxes AS taxes, buy.net AS net FROM balance_trades bal JOIN buy_trades buy ON bal.buyTradeId = buy.id JOIN equities eq ON eq.symbol = bal.equitySymbol;";

    String ADD_TRADE = "INSERT INTO `sample`.`buy_trades` ( `equitySymbol`, `date`, `exchange`, `price`, `qty`, `target`, `brokerage`, `pbt`, `brokerageAmount`, `taxes`, `net`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    String GET_BALANCE_TRADE = "SELECT qty FROM `sample`.`balance_trades` WHERE buyTradeId = ?;";

    String INSERT_BALANCE_TRADE = "INSERT INTO `sample`.`balance_trades` (`buyTradeId`, `equitySymbol`, `exchange`, `qty`) VALUES ( ?, ?, ?, ?);";

    String GET_EQUITY = "SELECT * FROM sample.equities WHERE symbol = ? AND mcSymbol = ?;";

    String ADD_EQUITY = "INSERT INTO `sample`.`equities` (`symbol`, `mcSymbol`, `name`, `sector`) VALUES ( ?, ?, ?, ?);";

    String UPDATE_BALANCE_TRADE = "UPDATE `sample`.`balance_trades` SET `qty` = ? WHERE `buyTradeId` = ?;";

    String DELETE_BALANCE_TRADE = "DELETE FROM `sample`.`balance_trades` WHERE buyTradeId = ?;";

    String INSERT_SELL_TRADES = "INSERT INTO `sample`.`sell_trades` ( `buyTradeId`, `date`, `price`, `qty`, `margin`, `brokerage`, `pbt`, `brokerageAmount`, `taxes`, `net`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
}
