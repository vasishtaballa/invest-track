package dev.vasishta.invest.track.dao;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.StatementItem;

import java.util.List;

public interface StatementDAO {
    List<StatementItem> getStatement(BaseBean baseBean);
}
