package com.example.tpsqlspring.mapper;

import com.example.tpsqlspring.dto.CommentaireDTO;
import com.example.tpsqlspring.entity.Commentaire;
import org.springframework.stereotype.Component;

@Component
public class CommentaireMapper {

    public CommentaireDTO toDTO(Commentaire commentaire) {
        if (commentaire == null) {
            return null;
        }
        CommentaireDTO dto = new CommentaireDTO();
        dto.setId(commentaire.getId());
        dto.setContenu(commentaire.getContenu());
        dto.setEmail(commentaire.getEmail());
        dto.setNom(commentaire.getNom());
        return dto;
    }

    public Commentaire toEntity(CommentaireDTO dto) {
        if (dto == null) {
            return null;
        }
        Commentaire commentaire = new Commentaire();
        commentaire.setId(dto.getId());
        commentaire.setContenu(dto.getContenu());
        commentaire.setEmail(dto.getEmail());
        commentaire.setNom(dto.getNom());
        return commentaire;
    }
}
