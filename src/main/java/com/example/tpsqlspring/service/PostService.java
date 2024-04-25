package com.example.tpsqlspring.service;

import com.example.tpsqlspring.entity.Post;
import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post findById(Long id);

    Post save(Post post);

    Post update(Long id, Post updatedPost);

    String deleteById(Long id);
}
