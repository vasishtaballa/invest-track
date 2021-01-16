package dev.vasishta.invest.track.service;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Stock;
import dev.vasishta.invest.track.bean.Trade;
import dev.vasishta.invest.track.dao.PortfolioDAO;
import dev.vasishta.invest.track.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioDAO portfolioDAO;

    @Autowired
    RestTemplate template;

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
            stocks.add(getStockObject(key, value));
        });
        return stocks;
    }

    private Stock getStockObject(String equitySymbol, List<Trade> trades) {
        Stock stock = new Stock();
        stock.setEquitySymbol(equitySymbol);
        stock.setEquityName(trades.get(0).getEquityName());
        stock.setMcSymbol(trades.get(0).getMcSymbol());
        stock.setTrades(trades);
        double avgPrice = 0, netPrice = 0, netValue = 0, margin = 0, netPL = 0;
        int balanceQty = 0, buyQty = 0;
        for (Trade trade : trades) {
            balanceQty += trade.getBalQty();
            buyQty += trade.getBuyQty();
            double temp = trade.getNet() / trade.getBuyQty();
            netPrice += (temp * trade.getBalQty());
        }
        avgPrice = netPrice / balanceQty;
        stock.setAvgPrice(avgPrice);
        stock.setQty(balanceQty);
        double currentPrice = GeneralUtils.getCurrentPriceFromMC(template, trades.get(0).getExchange(), trades.get(0).getMcSymbol());
        stock.setCurrentPrice(currentPrice);
        stock.setNetPrice(netPrice);
        double currentValue = currentPrice * balanceQty;
        netValue = currentValue - (currentValue * 0.006);
        stock.setNetValue(netValue);
        margin = Math.round(((netValue - netPrice) / netPrice) * 100 * 100.0) / 100.0;
        stock.setMargin(margin);
        netPL = currentValue - netPrice;
        stock.setNetPL(netPL);
        return stock;
    }
}