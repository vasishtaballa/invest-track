package dev.vasishta.invest.track.service;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Stock;
import dev.vasishta.invest.track.bean.Trade;
import dev.vasishta.invest.track.dao.PortfolioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioDAO portfolioDAO;

    public List<Trade> getPortfolio(BaseBean baseBean) {
        return portfolioDAO.getPortfolio();
    }

    public List<Stock> getPortfolioSummary(BaseBean baseBean) {
        List<Trade> trades = portfolioDAO.getPortfolio();
        List<Stock> stocks = new ArrayList<>();
        Map<String, List<Trade>> map = new HashMap<>();
        trades.forEach(trade -> {
            if (!map.containsKey(trade.getEquitySymbol())) {
                map.put(trade.getEquitySymbol(), new ArrayList<>());
            }
            map.get(trade.getEquitySymbol()).add(trade);
        });
        map.forEach((key, value) -> {
            getStockObject(key, value);
        });
        return null;
    }

    private void getStockObject(String key, List<Trade> value) {
        Stock stock = new Stock();
        stock.setEquitySymbol(key);
        stock.setEquityName(value.get(0).getEquityName());
    }
}