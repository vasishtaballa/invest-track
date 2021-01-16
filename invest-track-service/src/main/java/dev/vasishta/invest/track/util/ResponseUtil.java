package dev.vasishta.invest.track.util;

import dev.vasishta.invest.track.bean.Message;
import dev.vasishta.invest.track.constant.MessageType;

public final class ResponseUtil {
    public static Message getMessageObj(String msg, MessageType type) {
        Message message = new Message();
        message.setMessage(msg);
        message.setType(type.toString());
        return message;
    }
}
