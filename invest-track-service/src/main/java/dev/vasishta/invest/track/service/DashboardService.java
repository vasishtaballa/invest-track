package dev.vasishta.invest.track.service;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.dao.DashboardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    DashboardDAO dashboardDAO;

    public void getDashboard(BaseBean baseBean) {
        dashboardDAO.getDashboard(baseBean);
    }
}
