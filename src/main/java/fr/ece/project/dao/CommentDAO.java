package fr.ece.project.dao;

import fr.ece.project.models.Comment;
import java.util.List;

public interface CommentDAO {
    List<Comment> getByTask(String taskId);
    boolean save(Comment c);
    boolean delete(String id);
}
