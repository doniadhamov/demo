package com.example.demo.utils;

import com.example.demo.configurations.ProjectUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

    public static Long getUserId() {
        ProjectUserDetails projectUserDetails = getUserDetails();
        if (projectUserDetails != null) {
            return projectUserDetails.getUserId();
        }
        return null;
    }

    public static String getUsername() {
        ProjectUserDetails projectUserDetails = getUserDetails();
        if (projectUserDetails != null) {
            return projectUserDetails.getUsername();
        }
        return null;
    }

    public static ProjectUserDetails getUserDetails() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof ProjectUserDetails) {
                return (ProjectUserDetails) authentication.getPrincipal();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
