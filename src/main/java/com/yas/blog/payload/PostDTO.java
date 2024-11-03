package com.yas.blog.payload;


import com.yas.blog.entity.Posts;
import lombok.Data;

@Data
public class PostDTO {

    private long id;
    private String title;
    private String description;
    private String content;



}
