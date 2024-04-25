package com.example.tpsqlspring.service.impl;

import com.example.tpsqlspring.entity.Commentaire;
import com.example.tpsqlspring.entity.Post;
import com.example.tpsqlspring.repository.CommentaireRepository;
import com.example.tpsqlspring.repository.PostRepository;
import com.example.tpsqlspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentaireRepository commentaireRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        System.out.println("Oui");
        return postRepository.findById(id).orElse(null);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post update(Long id, Post updatedPost) {
        Post post = findById(id);
        if (post != null) {
            updatedPost.setId(id);
            return postRepository.save(updatedPost);
        }
        return null;
    }

    public String deleteById(Long id) {
        // Supprimer les commentaires associés au post
        List<Commentaire> commentaires = commentaireRepository.findAllByPostId(id);
        commentaireRepository.deleteAll(commentaires);

        // Supprimer le post lui-même
        postRepository.deleteById(id);

        return "Post deleted successfully";
    }
}
