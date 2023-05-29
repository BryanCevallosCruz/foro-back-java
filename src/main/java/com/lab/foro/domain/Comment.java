package com.lab.foro.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long id;
    private  String name;
    private String profilePhoto;
    private String comment;
    private String date;
    private List<Comment> commentSub;
}
