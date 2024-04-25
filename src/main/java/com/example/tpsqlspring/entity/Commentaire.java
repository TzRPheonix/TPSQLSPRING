package com.example.tpsqlspring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Le contenu doit avoir un minimum de 3 lettres")
    private String contenu;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "L'email doit être valide")
    @Pattern(regexp = ".+\\.net$", message = "L'email doit avoir une extension .net")
    private String email;

    @Size(min = 3, message = "Le nom doit avoir un minimum de 3 lettres")
    private String nom;

    // Mapping avec le post associé
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", email='" + email + '\'' +
                ", nom='" + nom + '\'' +
                ", post=" + post +
                '}';
    }
}