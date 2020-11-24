package dev.vasishta.invest.track.controller;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.service.TypeHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/typeHead")
public class TypeHeadController {

    @Autowired
    private TypeHeadService typeHeadService;

    @GetMapping("/getTypeHeads")
    public ResponseEntity<BaseBean> getTypeHeads() {
        BaseBean baseBean = new BaseBean();
        typeHeadService.getTypeHeads(baseBean);
        return new ResponseEntity<>(baseBean, HttpStatus.OK);
    }
}
