package com.minierp.dao;

import com.minierp.models.User;
import java.util.List;

public interface UserDAO {
    User findByUsername(String username);
    List<User> getAll();
    boolean save(User user);
}
