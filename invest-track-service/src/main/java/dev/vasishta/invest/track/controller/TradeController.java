package dev.vasishta.invest.track.controller;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.BuyTrade;
import dev.vasishta.invest.track.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/addTrade")
    public ResponseEntity<BaseBean> addTrade(@RequestBody BuyTrade buyTrade) {
        BaseBean baseBean = new BaseBean();
        tradeService.addTrade(buyTrade, baseBean);
        return new ResponseEntity<BaseBean>(baseBean, HttpStatus.OK);
    }
}
