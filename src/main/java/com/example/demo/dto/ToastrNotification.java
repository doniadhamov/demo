package com.example.demo.dto;

import com.example.demo.constants.ToastrNotificationType;

public class ToastrNotification {

    private ToastrNotificationType type;
    private String message;

    public ToastrNotification(ToastrNotificationType newType, String newMessage) {
        type = newType;
        message = newMessage;
    }

    public String getType() {
        return type.name();
    }

    public void setType(ToastrNotificationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
