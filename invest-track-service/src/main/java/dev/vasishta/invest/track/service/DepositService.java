package dev.vasishta.invest.track.service;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Deposit;
import dev.vasishta.invest.track.dao.DepositDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {

    @Autowired
    DepositDAO depositDAO;

    public void addDeposit(Deposit deposit, BaseBean baseBean) {
        depositDAO.addDeposit(deposit, baseBean);
    }

    public void getDeposits(BaseBean baseBean) {
        depositDAO.getDeposits(baseBean);
    }
}
