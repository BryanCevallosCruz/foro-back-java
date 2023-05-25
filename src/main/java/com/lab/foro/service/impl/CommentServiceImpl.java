package com.lab.foro.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.foro.domain.Comment;
import com.lab.foro.service.CommentService;
import com.lab.foro.service.dto.CommentSaveDto;
import com.lab.foro.service.dto.CommentUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    ObjectMapper objectMapper = new ObjectMapper();
    String filePath = "src/main/resources/datosComments.json";
    File jsonFile = new File(filePath);
    //List<Comment> comments;

    private List<Comment> InitializeJson() throws IOException{
        List<Comment> comments;
        if(jsonFile.exists()) {
            comments = objectMapper.readValue(jsonFile, new TypeReference<List<Comment>>() {});
        } else {
            comments = new ArrayList<>();
            jsonFile.createNewFile();
            objectMapper.writeValue(jsonFile,comments);
        }

        return comments;
    }

    @Override
    public List<Comment> getCommentsFromJsonFile() throws IOException {
        List<Comment> comments = InitializeJson();
        return comments;
    }

    @Override
    public Comment getCommentById(Long idComment) throws IOException{
        List<Comment> commentList = getCommentsFromJsonFile();
        Comment comment = commentList.stream()
                .filter(c -> c.getId().equals(idComment))
                .findFirst()
                .orElse(null);

        return comment;
    }

    @Override
    public void updateCommentById(Long id, CommentUpdateDto comment) throws IOException {
        List<Comment> comments = InitializeJson();
        for(Comment commentEdit: comments)
        {
            if (commentEdit.getId() == id)
            {
                commentEdit.setComment(comment.getComment());
                break;
            }
        }

        objectMapper.writeValue(jsonFile,comments);

    }

    @Override
    public void saveComment(Comment comment) throws IOException {
        List<Comment> comments = InitializeJson();
        comments.add(comment);
        objectMapper.writeValue(jsonFile, comments);
    }

    @Override
    public void deleteComment(Long idComment) throws IOException {
        List<Comment> comments = InitializeJson();
        comments.removeIf(c->c.getId()==idComment);
        objectMapper.writeValue(jsonFile,comments);
    }

}
