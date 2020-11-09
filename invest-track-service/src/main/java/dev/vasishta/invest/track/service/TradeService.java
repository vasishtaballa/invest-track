package dev.vasishta.invest.track.service;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.BuyTrade;
import dev.vasishta.invest.track.bean.Message;
import dev.vasishta.invest.track.dao.TradeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    @Autowired
    private TradeDAO tradeDAO;

    public void addTrade(BuyTrade buyTrade, BaseBean baseBean) {
        Message message = tradeDAO.addTrade(buyTrade);
        baseBean.getMessages().add(message);
    }
}
