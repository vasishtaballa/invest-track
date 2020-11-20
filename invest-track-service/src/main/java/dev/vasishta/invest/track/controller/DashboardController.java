package dev.vasishta.invest.track.controller;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/getDashboard")
    public ResponseEntity<BaseBean> getDashboardDetails() {
        BaseBean baseBean = new BaseBean();
        dashboardService.getDashboard(baseBean);
        return new ResponseEntity<>(baseBean, HttpStatus.OK);
    }
}
