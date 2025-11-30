package com.minierp.services;

import com.minierp.dao.impl.UserDAOImpl;
import com.minierp.models.User;
import com.minierp.utils.HashUtil;
import com.minierp.utils.Session;

public class AuthService {

    private final UserDAOImpl userDAO = new UserDAOImpl();

    public boolean login(String username, String password) {
        User u = userDAO.findByUsername(username);

        if (u == null) return false;

        if (HashUtil.verify(password, u.getPasswordHash())) {
            Session.setCurrentUser(u);
            return true;
        }

        return false;
    }
}
