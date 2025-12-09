package fr.ece.project.dao;

import fr.ece.project.models.User;
import java.util.List;

public interface UserDAO {
    User findByEmail(String email);
    List<User> getAll();
    boolean save(User user);
}

