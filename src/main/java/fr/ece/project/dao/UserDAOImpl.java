package fr.ece.project.dao;

import fr.ece.project.models.User;
import fr.ece.project.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl {

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT id, name, surname, email, password_hash, role FROM users";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getString("id"));
                u.setName(rs.getString("name"));
                u.setUsername(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public User findByEmail(String email) {
        String sql = "SELECT id, name, surname, email, password_hash, role FROM users WHERE email = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getString("id"));
                    u.setName(rs.getString("name"));
                    u.setUsername(rs.getString("surname"));
                    u.setEmail(rs.getString("email"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setRole(rs.getString("role"));
                    return u;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }


    public boolean save(User u) {
        String sql = "INSERT INTO users (id, name, surname, email, password_hash, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getId());
            ps.setString(2, u.getName());
            ps.setString(3, u.getSurname());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPasswordHash());
            ps.setString(6, u.getRole());
            return ps.executeUpdate() == 1;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean update(User u) {
        String sql = "UPDATE users SET name = ?, surname = ?, email = ?, password_hash = ?, role = ? WHERE id = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPasswordHash());
            ps.setString(5, u.getRole());
            ps.setString(6, u.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
