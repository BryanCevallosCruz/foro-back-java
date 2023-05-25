package com.lab.foro.controller;

import com.lab.foro.domain.Comment;
import com.lab.foro.service.dto.CommentUpdateDto;
import com.lab.foro.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable("id") Long id) throws IOException {
        return commentService.getCommentById(id);
    }

    @PutMapping("/{id}")
    public void updateComment(@PathVariable Long id,
                              @RequestBody CommentUpdateDto comment) throws Exception
    {
        commentService.updateCommentById(id, comment);
    }

    @PostMapping
    public void postComment(@RequestBody Comment comment) throws IOException{
        commentService.saveComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) throws IOException
    {
        commentService.deleteComment(id);
    }
    @PutMapping("/comment/{id}")
    public void putNewSubComment(@PathVariable Long id,
                                 @RequestBody Comment comment) throws IOException{
        commentService.saveCommentSub1(id, comment);
    }

    @PutMapping("/comment/{id}/{idSub1}")
    public void putNewSubSubComment(@PathVariable Long id,
                                    @PathVariable Long idSub1,
                                    @RequestBody Comment comment) throws IOException{
        commentService.saveCommentSub2(id, idSub1, comment);
    }

}
