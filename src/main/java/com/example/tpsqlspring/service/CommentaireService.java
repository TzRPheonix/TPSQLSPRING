package com.example.tpsqlspring.service;

import com.example.tpsqlspring.entity.Commentaire;

import java.util.List;

public interface CommentaireService {

    List<Commentaire> findAll();

    List<Commentaire> findAllByPostId(Long postId);

    Commentaire findByIdAndPostId(Long id, Long postId);

    Commentaire save(Long postId, Commentaire commentaire);

    Commentaire update(Long id, Long postId, Commentaire updatedCommentaire);

    String deleteByIdAndPostId(Long id, Long postId);
}
