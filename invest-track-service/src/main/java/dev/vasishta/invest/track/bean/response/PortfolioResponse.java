package dev.vasishta.invest.track.bean.response;

import dev.vasishta.invest.track.bean.BaseBean;
import dev.vasishta.invest.track.bean.Trade;
import lombok.Data;

import java.util.List;

@Data
public class PortfolioResponse extends BaseBean {
    private List<Trade> trades;
}
