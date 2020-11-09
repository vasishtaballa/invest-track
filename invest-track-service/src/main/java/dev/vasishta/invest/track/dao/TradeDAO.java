package dev.vasishta.invest.track.dao;

import dev.vasishta.invest.track.bean.BuyTrade;
import dev.vasishta.invest.track.bean.Message;

public interface TradeDAO {
    Message addTrade(BuyTrade buyTrade);
}
