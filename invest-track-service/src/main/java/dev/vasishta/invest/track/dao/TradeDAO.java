package dev.vasishta.invest.track.dao;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.BuyTrade;
import dev.vasishta.invest.track.bean.Message;
import dev.vasishta.invest.track.bean.SellTrade;

public interface TradeDAO {
    void addTrade(BuyTrade buyTrade, BaseBean baseBean);

    void sellEquity(SellTrade sellTrade, BaseBean baseBean);
}
