package com.example.demo.utils;

import com.example.demo.constants.ToastrNotificationType;
import com.example.demo.dto.ToastrNotification;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

public class ToastrNotificationUtils {
    private static final String modelParameterName = "toaster_notifications";

    public static void addMessage(RedirectAttributes model, ToastrNotificationType type, String message) {
        List<ToastrNotification> toastrNotificationList;
        if (!model.containsAttribute(modelParameterName)) {
            toastrNotificationList = new LinkedList<>();
        } else {
            toastrNotificationList = (List<ToastrNotification>) model.asMap().get(modelParameterName);
        }
        toastrNotificationList.add(new ToastrNotification(type, message));
        model.addFlashAttribute(modelParameterName, toastrNotificationList);
    }

    public static void addSuccess(RedirectAttributes model, String message) {
        addMessage(model, ToastrNotificationType.success, message);
    }

    public static void addInfo(RedirectAttributes model, String message) {
        addMessage(model, ToastrNotificationType.info, message);
    }

    public static void addWarning(RedirectAttributes model, String message) {
        addMessage(model, ToastrNotificationType.warning, message);
    }

    public static void addError(RedirectAttributes model, String message) {
        addMessage(model, ToastrNotificationType.error, message);
    }
}
