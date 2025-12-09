package fr.ece.project.services;

import fr.ece.project.dao.UserDAOImpl;
import fr.ece.project.models.User;
import fr.ece.project.utils.HashUtil;
import fr.ece.project.utils.Session;

public class AuthService {
    private final UserDAOImpl userDAO = new UserDAOImpl();

    public boolean login(String email, String password) {
        User u = userDAO.findByEmail(email);

        if (u == null) return false;
        System.out.println("password saisi = " + password);
        System.out.println("hash BDD       = " + u.getPasswordHash());
        System.out.println("hash(password) = " + HashUtil.hash(password));


        if (HashUtil.verify(password, u.getPasswordHash())) {
            Session.setCurrentUser(u);
            return true;
        }


        return false;
    }
}

