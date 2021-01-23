package dev.vasishta.invest.track.controller;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("misc")
public class GenericController {

    @Autowired
    private GenericService genericService;

    @GetMapping("/getEquityPL")
    public ResponseEntity<BaseBean> getEquityPL() {
        BaseBean baseBean = new BaseBean();
        genericService.getEquityPL(baseBean);
        return new ResponseEntity<>(baseBean, HttpStatus.OK);
    }
}
