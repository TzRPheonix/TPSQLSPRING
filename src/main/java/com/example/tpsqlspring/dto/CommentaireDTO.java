package com.example.tpsqlspring.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentaireDTO {
    private Long id;
    private String contenu;
    private String email;
    private String nom;
    private String dateCreation; // Affichage seulement
}
