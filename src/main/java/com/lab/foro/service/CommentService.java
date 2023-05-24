package com.lab.foro.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.foro.domain.Comment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {
    public List<Comment> getCommentsFromJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("src/main/resources/datosComments.json");
        List<Comment>  comments = objectMapper.readValue(jsonFile, new TypeReference<List<Comment>>() {});

        return comments;
    }
}
