package dev.vasishta.invest.track.controller;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Deposit;
import dev.vasishta.invest.track.constant.ResponseMessages;
import dev.vasishta.invest.track.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @PostMapping("/addDeposit")
    public ResponseEntity<BaseBean> addDeposit(@RequestBody Deposit deposit) {
        BaseBean baseBean = new BaseBean();
        depositService.addDeposit(deposit, baseBean);
        return new ResponseEntity<>(baseBean, HttpStatus.OK);
    }

    @GetMapping("/getDeposits")
    public ResponseEntity<BaseBean> getDeposits() {
        BaseBean baseBean = new BaseBean();
        depositService.getDeposits(baseBean);
        return new ResponseEntity<>(baseBean, HttpStatus.OK);
    }
}
