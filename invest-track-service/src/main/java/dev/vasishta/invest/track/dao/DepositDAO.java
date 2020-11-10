package dev.vasishta.invest.track.dao;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Deposit;

public interface DepositDAO {
    void addDeposit(Deposit deposit, BaseBean baseBean);

    void getDeposits(BaseBean baseBean);
}
