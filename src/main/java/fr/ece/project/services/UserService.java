package fr.ece.project.services;

import fr.ece.project.dao.UserDAOImpl;
import fr.ece.project.models.User;

import java.util.List;

public class UserService {
    private final UserDAOImpl userDAO = new UserDAOImpl();

    public List<User> getAllUsers() { return userDAO.getAll(); }
    public User findByUsername(String username) { return userDAO.findByUsername(username); }
    public boolean createUser(User u) { return userDAO.save(u); }
}

