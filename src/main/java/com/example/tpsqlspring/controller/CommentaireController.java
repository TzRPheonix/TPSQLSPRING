package com.example.tpsqlspring.controller;

import com.example.tpsqlspring.dto.CommentaireDTO;
import com.example.tpsqlspring.entity.Commentaire;
import com.example.tpsqlspring.mapper.CommentaireMapper;
import com.example.tpsqlspring.service.CommentaireService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    private CommentaireMapper commentaireMapper;

    @GetMapping
    public List<CommentaireDTO> getAllCommentairesByPostId(@RequestParam Long postId) {
        List<Commentaire> commentaires = commentaireService.findAllByPostId(postId);
        return commentaires.stream()
                .map(commentaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentaireDTO> getCommentaireByIdAndPostId(@PathVariable Long id, @RequestParam Long postId) {
        Commentaire commentaire = commentaireService.findByIdAndPostId(id, postId);
        if (commentaire != null) {
            return ResponseEntity.status(HttpStatus.OK).body(commentaireMapper.toDTO(commentaire));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentaireDTO> updateCommentaire(@PathVariable Long id, @RequestParam Long postId, @Valid @RequestBody CommentaireDTO commentaireDTO) {
        Commentaire commentaire = commentaireMapper.toEntity(commentaireDTO);
        Commentaire updatedCommentaire = commentaireService.update(id, postId, commentaire);
        return ResponseEntity.ok(commentaireMapper.toDTO(updatedCommentaire));
    }

    @PostMapping
    public ResponseEntity<CommentaireDTO> createCommentaire(@RequestParam Long postId, @Valid @RequestBody CommentaireDTO commentaireDTO) {
        Commentaire commentaire = commentaireMapper.toEntity(commentaireDTO);
        try {
            return ResponseEntity.ok(commentaireMapper.toDTO(commentaireService.save(postId, commentaire)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable Long id, @RequestParam Long postId) {
        commentaireService.deleteByIdAndPostId(id, postId);
        return ResponseEntity.ok().build();
    }
}

