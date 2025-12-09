package fr.ece.project.dao;

import fr.ece.project.models.Task;
import fr.ece.project.services.ProjectService;
import fr.ece.project.models.Project;
import fr.ece.project.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    private final ProjectService projectService = new ProjectService();

    @Override
    public List<Task> getByUser(String userId) {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE assignedTo = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task t = new Task(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getDate("dueDate") != null ? rs.getDate("dueDate").toLocalDate() : null,
                        rs.getString("projectId"),
                        rs.getString("assignedTo")
                );
                t.setId(rs.getString("id"));
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public List<Task> getByProject(String projectId) {
        List<Task> list = new ArrayList<>();

        String sql = "SELECT id, title, description, status, dueDate, projectId, assignedTo FROM tasks WHERE projectId = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, projectId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Task t = new Task(
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("status"),
                            rs.getDate("dueDate") != null ? rs.getDate("dueDate").toLocalDate() : null,
                            rs.getString("projectId"),
                            rs.getString("assignedTo")
                    );
                    t.setId(rs.getString("id"));
                    list.add(t);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean save(Task task) {
        // Générer un ID unique pour la tâche
        task.setId(java.util.UUID.randomUUID().toString());

        // Récupérer l'utilisateur assigné automatiquement depuis le projet
        Project project = projectService.findById(task.getProjectId());
        if (project == null) {
            System.out.println("Erreur : projet introuvable !");
            return false;
        }
        task.setAssignedTo(project.getManagerId()); // assignation automatique

        String sql = "INSERT INTO tasks (id, title, description, status, dueDate, projectId, assignedTo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, task.getId());
            ps.setString(2, task.getTitle());
            ps.setString(3, task.getDescription());
            ps.setString(4, task.getStatus());
            if (task.getDueDate() != null) {
                ps.setDate(5, java.sql.Date.valueOf(task.getDueDate()));
            } else {
                ps.setDate(5, null);
            }
            ps.setString(6, task.getProjectId());
            ps.setString(7, task.getAssignedTo());

            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Task task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, status = ?, dueDate = ?, projectId = ?, assignedTo = ? WHERE id = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus());
            if (task.getDueDate() != null) {
                ps.setDate(4, java.sql.Date.valueOf(task.getDueDate()));
            } else {
                ps.setDate(4, null);
            }
            ps.setString(5, task.getProjectId());
            ps.setString(6, task.getAssignedTo());
            ps.setString(7, task.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(String taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, taskId);
            return ps.executeUpdate() == 1;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> list = new ArrayList<>();

        String sql = "SELECT id, title, description, status, dueDate, projectId, assignedTo FROM tasks";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Task t = new Task(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getDate("dueDate") != null ? rs.getDate("dueDate").toLocalDate() : null,
                        rs.getString("projectId"),
                        rs.getString("assignedTo")
                );
                t.setId(rs.getString("id"));
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
