package com.yas.blog.service.impl;

import com.yas.blog.entity.Posts;
import com.yas.blog.exception.ResourceNotFoundException;
import com.yas.blog.payload.PostDTO;
import com.yas.blog.repository.PostRepository;
import com.yas.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository)
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


    public PostDTO getPostByID(long id)
    {
        Posts post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "Id", id));
        PostDTO postDTO = post2PostDTO(post);
        return postDTO;
    }


    @Override
    public PostDTO updatePostByID(PostDTO postDTO, long id) {

        if(postRepository.existsById(id) == false)
            throw new ResourceNotFoundException("Post", "id", id);

        Posts post = postDTO2Post(postDTO);

        return post2PostDTO(postRepository.save(post));


    }

    @Override
    public void deletePostByID(long id) {

        if(postRepository.existsById(id) == false)
            throw new ResourceNotFoundException("Post", "id", id);

        postRepository.deleteById(id);

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
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        return post;
    }




}
