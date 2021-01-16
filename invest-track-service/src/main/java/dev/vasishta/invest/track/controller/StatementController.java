package dev.vasishta.invest.track.controller;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statement")
public class StatementController {

    @Autowired
    private StatementService statementService;

    @GetMapping("/getStatement")
    public ResponseEntity<BaseBean> getStatement() {
        BaseBean baseBean = new BaseBean();
        statementService.getStatement(baseBean);
        return new ResponseEntity<BaseBean>(baseBean, HttpStatus.OK);
    }
}
