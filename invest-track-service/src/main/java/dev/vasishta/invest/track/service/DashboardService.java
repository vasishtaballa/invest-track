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
        Dashboard dashboard = dashboardDAO.getDashboard(baseBean);
        getDashboardItems(dashboard);
    }

    private void getDashboardItems(Dashboard dashboard) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.convertValue(dashboard, Map.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {

        }
    }
}
