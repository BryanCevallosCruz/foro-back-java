package com.lab.foro.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.foro.domain.Comment;
import com.lab.foro.service.CommentService;
import com.lab.foro.service.dto.CommentUpdateDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {



    @Override
    public List<Comment> getCommentsFromJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("src/main/resources/datosComments.json");
        List<Comment>  comments = objectMapper.readValue(jsonFile, new TypeReference<List<Comment>>() {});

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
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("src/main/resources/datosComments.json");
        List<Comment>  comments = objectMapper.readValue(jsonFile, new TypeReference<List<Comment>>() {});

        for(Comment commentEdit: comments)
        {
            if (commentEdit.getId() == id)
            {
                commentEdit.setComment(comment.getComment());
                break;
            }
        }

        objectMapper.writeValue(new File("src/main/resources/datosComments.json"),comments);

    }

    @Override
    public List<Comment> getAll()
    {
        return null;
    }

}
