package com.example.tpsqlspring.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String titre;
    private String contenu;
    private String dateCreation; // Affichage seulement
}
