package fr.ece.project.dao;

import fr.ece.project.models.Project;
import fr.ece.project.utils.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO {

    @Override
    public List<Project> getAll() {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Project p = new Project(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("managerId"),
                        rs.getDate("startDate") != null ? rs.getDate("startDate").toLocalDate() : null,
                        rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : null
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Project findById(String id) {
        String sql = "SELECT * FROM projects WHERE id = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Project(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("managerId"),
                            rs.getDate("startDate") != null ? rs.getDate("startDate").toLocalDate() : null,
                            rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : null
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Project p) {
        String sql = "INSERT INTO projects (id, name, description, managerId, startDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getDescription());
            ps.setString(4, p.getManagerId());
            ps.setDate(5, Date.valueOf(p.getStartDate()));
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Project p) {
        String sql = "UPDATE projects SET name = ?, description = ?, managerId = ?, startDate = ?, endDate = ? WHERE id = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getManagerId());
            ps.setDate(4, p.getStartDate() != null ? Date.valueOf(p.getStartDate()) : null);
            ps.setDate(5, p.getEndDate() != null ? Date.valueOf(p.getEndDate()) : null);
            ps.setString(6, p.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM projects WHERE id = ?";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
