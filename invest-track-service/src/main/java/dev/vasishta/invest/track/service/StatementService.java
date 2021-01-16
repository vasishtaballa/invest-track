package dev.vasishta.invest.track.service;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.StatementItem;
import dev.vasishta.invest.track.dao.StatementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatementService {

    @Autowired
    private StatementDAO statementDAO;

    public void getStatement(BaseBean baseBean) {
        List<StatementItem> statement = statementDAO.getStatement(baseBean);
        baseBean.setResponse(statement);
    }
}
