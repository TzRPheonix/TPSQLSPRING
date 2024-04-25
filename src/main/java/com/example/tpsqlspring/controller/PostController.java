package com.example.tpsqlspring.controller;

import com.example.tpsqlspring.dto.CommentaireDTO;
import com.example.tpsqlspring.dto.PostDTO;
import com.example.tpsqlspring.entity.Commentaire;
import com.example.tpsqlspring.entity.Post;
import com.example.tpsqlspring.mapper.CommentaireMapper;
import com.example.tpsqlspring.mapper.PostMapper;
import com.example.tpsqlspring.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentaireMapper commentaireMapper;

    @GetMapping("/{postId}/comments")
    public List<CommentaireDTO> getAllCommentsByPostId(@PathVariable("postId") Long postId) {
        List<Commentaire> commentaires = postService.getAllCommentsByPostId(postId);
        return commentaires.stream()
                .map(commentaire -> commentaireMapper.toDTO(commentaire))
                .collect(Collectors.toList());
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentaireDTO> addCommentToPost(@PathVariable("postId") Long postId, @Valid @RequestBody CommentaireDTO commentaireDTO) {
        Commentaire commentaire = commentaireMapper.toEntity(commentaireDTO);
        Commentaire addedComment = postService.addCommentToPost(postId, commentaire);
        if (addedComment != null) {
            return ResponseEntity.ok(commentaireMapper.toDTO(addedComment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postService.findAll();
        return posts.stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id) {
        Post post = postService.findById(id);
        if (post != null) {
            return ResponseEntity.status(HttpStatus.OK).body(postMapper.toDTO(post));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @Valid @RequestBody PostDTO postDTO) {
        Post post = postMapper.toEntity(postDTO);
        Post updatedPost = postService.update(id, post);
        return ResponseEntity.ok(postMapper.toDTO(updatedPost));
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        Post post = postMapper.toEntity(postDTO);
        try {
            return ResponseEntity.ok(postMapper.toDTO(postService.save(post)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
