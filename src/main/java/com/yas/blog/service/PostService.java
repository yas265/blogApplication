package com.yas.blog.service;

import com.yas.blog.entity.Posts;
import com.yas.blog.payload.PostDTO;
import com.yas.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository)
    {
        this.postRepository = postRepository;
    }

    public PostDTO createPost(PostDTO postDTO)
    {
        Posts post = new Posts();
        post = postDTO2Post(postDTO);
        return post2PostDTO(postRepository.save(post));
    }


    public List<PostDTO> getAll()
    {
        List<Posts> posts = new ArrayList<>();
        posts = (List<Posts>)postRepository.findAll();
        List<PostDTO> postDtos = new ArrayList<>();

        for(Posts post : posts)
        {
            postDtos.add(post2PostDTO(post));
        }
        return postDtos;
    }



    public PostDTO post2PostDTO(Posts post)
    {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());

        return postDTO;
    }

    public Posts postDTO2Post(PostDTO postDTO)
    {
        Posts post = new Posts();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        return post;
    }




}
