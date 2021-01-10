package dev.vasishta.invest.track.controller;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Trade;
import dev.vasishta.invest.track.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/getPortfolio")
    public ResponseEntity<BaseBean> getPortfolio() {
        BaseBean baseBean = new BaseBean();
        List<Trade> trades = portfolioService.getPortfolio(baseBean);
        baseBean.setResponse(trades);
        return new ResponseEntity<>(baseBean, HttpStatus.OK);
    }

    @GetMapping("/getPortfolioSummary")
    public ResponseEntity<BaseBean> getPortfolioSummary() {
        BaseBean baseBean = new BaseBean();
        portfolioService.getPortfolioSummary(baseBean);
        return null;
    }
}
