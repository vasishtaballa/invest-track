package dev.vasishta.invest.track.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Dashboard;
import dev.vasishta.invest.track.dao.DashboardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    DashboardDAO dashboardDAO;

    public void getDashboard(BaseBean baseBean) {
        dashboardDAO.getDashboard(baseBean);
    }
}
