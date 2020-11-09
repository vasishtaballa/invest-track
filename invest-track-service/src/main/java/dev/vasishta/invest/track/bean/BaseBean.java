package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseBean {
    private List<Message> messages = new ArrayList<>();
    private Object response;
}
