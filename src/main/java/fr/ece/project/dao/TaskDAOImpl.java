package fr.ece.project.dao;

import fr.ece.project.models.Task;
import fr.ece.project.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {
    @Override
    public List<Task> getByProject(int projectId) {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT id, title, status, project_id, assigned_to FROM tasks WHERE project_id = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Task t = new Task();
                    t.setId(rs.getInt("id"));
                    t.setTitle(rs.getString("title"));
                    t.setStatus(rs.getString("status"));
                    t.setProjectId(rs.getInt("project_id"));
                    t.setAssignedTo(rs.getInt("assigned_to"));
                    list.add(t);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean save(Task task) {
        String sql = "INSERT INTO tasks (title, status, project_id, assigned_to) VALUES (?, ?, ?, ?)";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getStatus());
            ps.setInt(3, task.getProjectId());
            ps.setInt(4, task.getAssignedTo());
            return ps.executeUpdate() == 1;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(Task task) {
        String sql = "UPDATE tasks SET title = ?, status = ?, project_id = ?, assigned_to = ? WHERE id = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getStatus());
            ps.setInt(3, task.getProjectId());
            ps.setInt(4, task.getAssignedTo());
            ps.setInt(5, task.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, taskId);
            return ps.executeUpdate() == 1;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}

