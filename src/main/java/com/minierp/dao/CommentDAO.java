package com.minierp.dao;

import com.minierp.models.Comment;
import java.util.List;

public interface CommentDAO {
    List<Comment> getByTask(int taskId);
    boolean save(Comment comment);
    boolean delete(int id);
}
