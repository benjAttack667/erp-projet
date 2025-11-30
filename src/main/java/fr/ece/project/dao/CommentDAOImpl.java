package fr.ece.project.dao;

import fr.ece.project.models.Comment;
import fr.ece.project.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {
    @Override
    public List<Comment> getByTask(int taskId) {
        List<Comment> list = new ArrayList<>();
        String sql = "SELECT id, task_id, user_id, content FROM comments WHERE task_id = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, taskId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comment cm = new Comment();
                    cm.setId(rs.getInt("id"));
                    cm.setTaskId(rs.getInt("task_id"));
                    cm.setUserId(rs.getInt("user_id"));
                    cm.setContent(rs.getString("content"));
                    list.add(cm);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean save(Comment c) {
        String sql = "INSERT INTO comments (task_id, user_id, content) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getTaskId());
            ps.setInt(2, c.getUserId());
            ps.setString(3, c.getContent());
            return ps.executeUpdate() == 1;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM comments WHERE id = ?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
