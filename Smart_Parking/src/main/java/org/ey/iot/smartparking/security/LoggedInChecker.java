package org.ey.iot.smartparking.security;

import org.ey.iot.smartparking.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class LoggedInChecker {
    public User getLoggedInUser() {
        User user = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            // principal can be "anonymousUser" (String)
            if (principal instanceof IOTUserDetails) {
                IOTUserDetails userDetails = (IOTUserDetails) principal;
                user = userDetails.getUser();
            }
        }

        return user;
    }
}
