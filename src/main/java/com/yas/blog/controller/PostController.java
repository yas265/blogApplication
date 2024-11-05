package com.yas.blog.controller;

import com.yas.blog.payload.PostDTO;
import com.yas.blog.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostServiceImpl postServiceImpl;

    @Autowired
    public PostController(PostServiceImpl postServiceImpl)
    {
        this.postServiceImpl = postServiceImpl;
    }

    @PostMapping("/")
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO)
    {
        return new ResponseEntity<>(postServiceImpl.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDTO>> getAllPosts()
    {
        List<PostDTO> allPosts = new ArrayList<>();
        allPosts = postServiceImpl.getAll();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") long id)
    {
        return new ResponseEntity<>(postServiceImpl.getPostByID(id), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postDTO, @PathVariable long id)
    {
        return new ResponseEntity<>(postServiceImpl.updatePostByID(postDTO, id), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable long id)
    {
        postServiceImpl.deletePostByID(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
