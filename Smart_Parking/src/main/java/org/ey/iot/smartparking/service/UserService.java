package org.ey.iot.smartparking.service;

import java.util.List;

import org.ey.iot.smartparking.model.User;


public interface UserService {
    User getUserByUsername(String username);

    List<String> getPermissions(String username);

    User getCurrentUser();

    Boolean isCurrentUserLoggedIn();
}
