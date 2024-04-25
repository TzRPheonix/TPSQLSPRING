package com.example.tpsqlspring.repository;

import com.example.tpsqlspring.entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

    List<Commentaire> findAllByPostId(Long postId);

    Commentaire findByIdAndPostId(Long id, Long postId);

    void deleteByIdAndPostId(Long id, Long postId);
}
