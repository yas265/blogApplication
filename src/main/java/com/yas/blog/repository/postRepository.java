package com.yas.blog.repository;

import com.yas.blog.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postRepository extends JpaRepository<Posts, Long> {
}
