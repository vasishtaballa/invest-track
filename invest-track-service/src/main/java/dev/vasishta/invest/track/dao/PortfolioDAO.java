package dev.vasishta.invest.track.dao;

import dev.vasishta.invest.track.bean.Trade;

import java.util.List;

public interface PortfolioDAO {
    List<Trade> getPortfolio();
}
