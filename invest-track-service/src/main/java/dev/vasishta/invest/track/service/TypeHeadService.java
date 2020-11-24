package dev.vasishta.invest.track.service;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.dao.TypeHeadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeHeadService {

    @Autowired
    private TypeHeadDAO typeHeadDAO;

    public void getTypeHeads(BaseBean baseBean) {
        typeHeadDAO.getTypeHeads(baseBean);
    }
}
