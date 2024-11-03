package com.yas.blog.controller;

import com.yas.blog.payload.PostDTO;
import com.yas.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService)
    {
        this.postService = postService;
    }

    @PostMapping("/")
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO)
    {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDTO>> getAllPosts()
    {
        List<PostDTO> allPosts = new ArrayList<>();
        allPosts = postService.getAll();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }



}
