package dev.vasishta.invest.track.service;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Trade;
import dev.vasishta.invest.track.dao.PortfolioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioDAO portfolioDAO;

    public List<Trade> getPortfolio(BaseBean baseBean) {
        return portfolioDAO.getPortfolio();
    }
}
