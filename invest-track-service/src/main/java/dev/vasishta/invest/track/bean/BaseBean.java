package dev.vasishta.invest.track.bean;

import lombok.Data;

import java.util.List;

@Data
public class BaseBean {
    private List<Message> messages;
    private Object response;
}
