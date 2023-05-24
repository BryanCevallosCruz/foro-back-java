package com.lab.foro.controller;

import com.lab.foro.domain.Comment;
import com.lab.foro.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping
    public List<Comment> getAllComments() throws IOException {
        return commentService.getCommentsFromJsonFile();
    }

    @GetMapping("/lista")
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable("id") Long id) throws IOException {
        return commentService.getCommentById(id);
    }
}
