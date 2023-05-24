package com.lab.foro.service;

import com.lab.foro.domain.Comment;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    List<Comment> getCommentsFromJsonFile() throws IOException;
    Comment getCommentById(Long id) throws IOException;

    void updateCommentById(Long id) throws IOException;

    List<Comment> getAll();
}
