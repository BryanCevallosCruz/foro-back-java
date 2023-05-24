package com.lab.foro.service;

import com.lab.foro.domain.Comment;
import com.lab.foro.service.dto.CommentSaveDto;
import com.lab.foro.service.dto.CommentUpdateDto;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    List<Comment> getCommentsFromJsonFile() throws IOException;
    Comment getCommentById(Long id) throws IOException;

    void updateCommentById(Long id, CommentUpdateDto comment) throws IOException;

    void saveComment(Comment comment) throws IOException;

    void deleteComment(Long id) throws IOException;

}
