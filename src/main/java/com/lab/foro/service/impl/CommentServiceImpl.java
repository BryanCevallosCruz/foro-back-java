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
        List<Comment> commentList = getCommentsFromJsonFile();
        Long idComment = getLastId(commentList);
        comment.setId(idComment);
        List<Comment> comments = InitializeJson();
        comments.add(comment);
        objectMapper.writeValue(jsonFile, comments);
    }

    public Long getLastId(List<Comment> commentList) throws IOException{
        Long idComment = 1L;
        if (commentList.size()>=1){
            int lastIndex = commentList.size() - 1;
            Comment lastComment = commentList.get(lastIndex);
            if (lastComment.getId()!=null){
                idComment = lastComment.getId()+1;
            }
        }
        return idComment;
    }

    @Override
    public void deleteComment(Long idComment) throws IOException {
        List<Comment> comments = InitializeJson();
        comments.removeIf(c->c.getId()==idComment);
        objectMapper.writeValue(jsonFile,comments);
    }

    @Override
    public void saveCommentSub1(Long id, Comment comment) throws IOException {
        List<Comment> comments = InitializeJson();
        List<Comment> commentSub1 = new ArrayList<>();

        for(Comment commentEdit: comments)
        {
            if (commentEdit.getId() == id)
            {
                if (commentEdit.getCommentSub()!=null){
                    for(Comment commentEditSub: commentEdit.getCommentSub()) {
                        commentSub1.add(commentEditSub);
                    }
                }
                Long idComment = getLastId(commentSub1);
                comment.setId(idComment);

                commentSub1.add(comment);
                commentEdit.setCommentSub(commentSub1);

                break;
            }
        }
        objectMapper.writeValue(jsonFile,comments);
    }

    @Override
    public void saveCommentSub2(Long id, Long idSub1, Comment comment) throws IOException {
        List<Comment> comments = InitializeJson();
        List<Comment> commentSub2 = new ArrayList<>();
        outerLoop:
        for(Comment commentEdit: comments)
        {
            if (commentEdit.getId() == id)
            {
                for(Comment commentEditSub1: commentEdit.getCommentSub()){
                    if(commentEditSub1.getId() == idSub1){
                        if(commentEditSub1.getCommentSub() != null){
                            for(Comment commentEditSub2 : commentEditSub1.getCommentSub()){
                                commentSub2.add(commentEditSub2);
                            }
                        }

                        Long idComment = getLastId(commentSub2);
                        comment.setId(idComment);

                        commentSub2.add(comment);
                        //commentEdit.setCommentSub(commentEditSub1.setCommentSub(commentSub2));
                        commentEditSub1.setCommentSub(commentSub2);
                        break outerLoop;
                    }
                }
            }
        }
        objectMapper.writeValue(jsonFile,comments);

    }

    @Override
    public void deleteCommentSub1(Long id, Long idSub1) throws IOException {
       //TO-DO: Implementar el método
    }

    @Override
    public void deleteCommentSub2(Long id, Long idSub1, Long idSub2) throws IOException {
        //TO-DO: Implementar el método
    }

}
