package dev.vasishta.invest.track.constant;

public interface ResponseMessages {

    String ADD_TRADE_SUCCESS = "Successfully added new trade";
    String ADD_TRADE_FAIL = "Failed to add new trade. Please try again later";

    String INSERT_BALANCE_TRADES_FAIL = "Failed to insert into balance trades. Please try again later";
    String INSERT_BALANCE_TRADES_SUCCESS = "Successfully inserted into balance trades";

    String UPDATE_BALANCE_TRADES_FAIL = "Failed to update balance trades. Please try again later";
    String UPDATE_BALANCE_TRADES_SUCCESS = "Successfully updated balance trades";
    String UPDATE_BALANCE_TRADES_INSUFFICIENT = "Requested amount of trades can't be sold. Please try with less amounts";

    String DELETE_BALANCE_TRADES_FAIL = "Failed to delete from balance trades. Please try again later";
    String DELETE_BALANCE_TRADES_SUCCESS = "Successfully deleted from balance trades";

    String INSERT_SELL_TRADES_FAIL = "Failed to insert into sell trades. Please try again later";
    String INSERT_SELL_TRADES_SUCCESS = "Successfully inserted into sell trades";

    String INSERT_DEPOSIT_FAIL = "Failed to insert into deposits. Please try again later";
    String INSERT_DEPOSIT_SUCCESS = "Successfully inserted into deposits";

    String GET_DEPOSIT_FAIL = "Failed to fetch deposits. Please try again later";

    String GET_DB_FAIL = "Failed to fetch dashboard details. Please try again later";

    String GET_TH_FAIL = "Failed to fetch suggestions. Please try again later. Failed for: ";

    String GET_STATEMENT_FAIL = "Failed to fetch statement. Please try again later";
}
