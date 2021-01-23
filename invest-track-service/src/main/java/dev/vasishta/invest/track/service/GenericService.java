package dev.vasishta.invest.track.service;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.dao.GenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenericService {

    @Autowired
    private GenericDAO genericDAO;

    public void getEquityPL(BaseBean baseBean) {
        genericDAO.getEquityPL(baseBean);
    }
}
