package com.example.tpsqlspring.service.impl;

import com.example.tpsqlspring.entity.Commentaire;
import com.example.tpsqlspring.entity.Post;
import com.example.tpsqlspring.repository.CommentaireRepository;
import com.example.tpsqlspring.repository.PostRepository;
import com.example.tpsqlspring.service.CommentaireService;
import com.example.tpsqlspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.SysexMessage;
import java.util.List;

@Service
public class CommentaireServiceImpl implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private PostService postService;

    public List<Commentaire> findAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public List<Commentaire> findAllByPostId(Long postId) {
        return commentaireRepository.findAllByPostId(postId);
    }

    @Override
    public Commentaire findByIdAndPostId(Long id, Long postId) {
        return commentaireRepository.findByIdAndPostId(id, postId);
    }

    @Override
    public Commentaire save(Long postId, Commentaire commentaire) {
        System.out.println("save");
        System.out.println(postId);
        Post post =  postService.findById(postId);
        System.out.println(post);
        if (post != null) {
            commentaire.setPost(post);
            return commentaireRepository.save(commentaire);
        } else {
            return null;
        }
    }

    @Override
    public Commentaire update(Long id, Long postId, Commentaire updatedCommentaire) {
        Commentaire commentaire = findByIdAndPostId(id, postId);
        if (commentaire != null) {
            updatedCommentaire.setId(id);
            updatedCommentaire.setPost(commentaire.getPost());
            return commentaireRepository.save(updatedCommentaire);
        }
        return null;
    }

    @Override
    public String deleteByIdAndPostId(Long id, Long postId) {
        commentaireRepository.deleteByIdAndPostId(id, postId);
        return "Comment deleted successfully";
    }
}
