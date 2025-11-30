package fr.ece.project.services;

import fr.ece.project.dao.UserDAOImpl;
import fr.ece.project.models.User;
import fr.ece.project.utils.HashUtil;
import fr.ece.project.utils.Session;

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

