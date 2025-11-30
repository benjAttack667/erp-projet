package fr.ece.project.dao;

import fr.ece.project.models.Comment;
import java.util.List;

public interface CommentDAO {
    List<Comment> getByTask(int taskId);
    boolean save(Comment c);
    boolean delete(int id);
}
