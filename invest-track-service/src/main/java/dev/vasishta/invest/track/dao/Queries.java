package dev.vasishta.invest.track.dao;

public interface Queries {
    String GET_PORTFOLIO = "SELECT buy.id AS id, buy.equitySymbol AS equitySymbol, eq.name AS equityName, eq.mcSymbol AS mcSymbol, buy.date AS date, buy.exchange AS exchange, buy.price AS price, bal.qty AS balQty, buy.qty as buyQty, buy.target AS target, buy.brokerage AS brokerage, buy.pbt AS pbt, buy.brokerageAmount AS brokerageAmount, buy.taxes AS taxes, buy.net AS net FROM balance_trades bal JOIN buy_trades buy ON bal.buyTradeId = buy.id JOIN equities eq ON eq.symbol = bal.equitySymbol ORDER BY buy.date;";

    String ADD_TRADE = "INSERT INTO `sample`.`buy_trades` ( `equitySymbol`, `date`, `exchange`, `price`, `qty`, `target`, `brokerage`, `pbt`, `brokerageAmount`, `taxes`, `net`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    String GET_BALANCE_TRADE = "SELECT qty FROM `sample`.`balance_trades` WHERE buyTradeId = ?;";

    String INSERT_BALANCE_TRADE = "INSERT INTO `sample`.`balance_trades` (`buyTradeId`, `equitySymbol`, `exchange`, `qty`) VALUES ( ?, ?, ?, ?);";

    String GET_EQUITY = "SELECT * FROM sample.equities WHERE symbol = ? AND mcSymbol = ?;";

    String ADD_EQUITY = "INSERT INTO `sample`.`equities` (`symbol`, `mcSymbol`, `name`, `sector`) VALUES ( ?, ?, ?, ?);";

    String UPDATE_BALANCE_TRADE = "UPDATE `sample`.`balance_trades` SET `qty` = ? WHERE `buyTradeId` = ?;";

    String DELETE_BALANCE_TRADE = "DELETE FROM `sample`.`balance_trades` WHERE buyTradeId = ?;";

    String INSERT_SELL_TRADES = "INSERT INTO `sample`.`sell_trades` ( `buyTradeId`, `date`, `price`, `qty`, `margin`, `brokerage`, `pbt`, `brokerageAmount`, `taxes`, `net`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    String ADD_DEPOSIT = "INSERT INTO `sample`.`deposits` (`date`, `amount`) VALUES (?, ?);";

    String GET_DEPOSITS = "SELECT * FROM `sample`.`deposits`";

    String DB_TOTAL_INVESTMENTS = "SELECT SUM(net) AS total_invested FROM buy_trades buy;";

    String DB_TOTAL_WITHDREW = "SELECT SUM(net) AS total_withdrew FROM sell_trades sell;";

    String DB_TOTAL_TAXES = "SELECT SUM(taxes) FROM buy_trades UNION SELECT SUM(taxes) FROM sell_trades;";

    String DB_TOTAL_BROKERAGE = "SELECT SUM(brokerageAmount) FROM buy_trades UNION SELECT SUM(brokerageAmount) FROM sell_trades;";

    String DB_OTHER_DETAILS = "SELECT bal.buyTradeId, bal.equitySymbol, eq.mcSymbol, buy.date, buy.exchange, buy.price, buy.qty, buy.target, buy.pbt, buy.brokerageAmount, buy.brokerage, buy.taxes, buy.net, bal.qty 'balQty' FROM buy_trades buy JOIN balance_trades bal ON bal.buyTradeId = buy.id JOIN equities eq ON buy.equitySymbol = eq.symbol;";

    String TOTAL_PROFIT_MARGIN = "SELECT buy.qty as buy_qty, sell.qty as sell_qty, buy.net as buy_net, sell.net as sell_net FROM sample.buy_trades buy JOIN sample.sell_trades sell ON buy.id = sell.buyTradeId;";

    String TOTAL_PROFIT_MARGIN_EQ_WISE = "SELECT equities.symbol AS eq_symbol, equities.name AS eq_name, buy.qty AS buy_qty, sell.qty AS sell_qty, buy.net AS buy_net, sell.net AS sell_net FROM sample.buy_trades buy JOIN sample.sell_trades sell ON buy.id = sell.buyTradeId JOIN sample.equities equities ON buy.equitySymbol = equities.symbol;";

    String TH_EQUITIES = "SELECT DISTINCT(name) FROM sample.equities;";

    String TH_SECTORS = "SELECT DISTINCT(sector) FROM sample.equities;";

    String TH_EQUITY_SYMBOLS = "SELECT DISTINCT(symbol) FROM sample.equities;";

    String TH_MC_EQUITY_SYMBOLS = "SELECT DISTINCT(mcSymbol) FROM sample.equities;";

    String TH_EXCHANGES = "SELECT DISTINCT(exchange) FROM sample.buy_trades;";

    String ADD_QUERY_UPDATE = "INSERT INTO `sample`.`query_updates` (`table`, `type`, `query`) VALUES (?, ?, ?);";

    String GET_TOTAL_DEPOSIT_AMOUNT = "SELECT SUM(amount) AS total_amount FROM deposits;";

    String GET_QUERY_UPDATES = "";

    String GET_BUY_STATEMENT = "SELECT buy.date, 'BUY' AS type, equity.name as equityName, buy.qty, buy.price, buy.net FROM buy_trades buy JOIN equities equity ON equity.symbol = buy.equitySymbol;";

    String GET_SELL_STATEMENT = "SELECT sell.date, 'SELL' AS type, equity.name as equityName, sell.qty, sell.price, sell.net FROM sell_trades sell JOIN buy_trades buy ON buy.id = sell.buyTradeId JOIN equities equity ON equity.symbol = buy.equitySymbol;";
}
