package com.yas.blog.service;

import com.yas.blog.payload.PostDTO;

import java.util.List;

public interface PostService {

    public PostDTO createPost(PostDTO postDTO);

    public List<PostDTO> getAll();

    public PostDTO getPostByID(long id);

    public PostDTO updatePostByID(PostDTO postDTO, long id);

    public void deletePostByID(long id);


}
