package com.example.tpsqlspring.ihm;

import com.example.tpsqlspring.entity.Commentaire;
import com.example.tpsqlspring.entity.Post;
import com.example.tpsqlspring.service.CommentaireService;
import com.example.tpsqlspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class IHM implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentaireService commentaireService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createPost(scanner);
                    break;
                case 2:
                    readPost(scanner);
                    break;
                case 3:
                    updatePost(scanner);
                    break;
                case 4:
                    deletePost(scanner);
                    break;
                case 5:
                    createCommentaire(scanner);
                    break;
                case 6:
                    readAllCommentairesOfPost(scanner);
                    break;
                case 7:
                    readCommentaireOfPost(scanner);
                    break;
                case 8:
                    updateCommentaire(scanner);
                    break;
                case 9:
                    deleteCommentaire(scanner);
                    break;
                case 10:
                    readAllPosts();
                    break;
                case 11:
                    readAllCommentaires();
                    break;
                case 12:
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }

        } while (choice != 11);
    }

    private void displayMainMenu() {
        System.out.println("\nMenu Principal:");
        System.out.println("1. Créer un post");
        System.out.println("2. Récupérer un post par son ID");
        System.out.println("3. Modifier un post");
        System.out.println("4. Supprimer un post");
        System.out.println("5. Créer un commentaire associé à un post");
        System.out.println("6. Récupérer tous les commentaires d'un post");
        System.out.println("7. Récupérer un commentaire d'un post par son ID");
        System.out.println("8. Modifier un commentaire");
        System.out.println("9. Supprimer un commentaire");
        System.out.println("10. Récupérer tous les posts");
        System.out.println("11. Récupérer tous les commentaires");
        System.out.println("12. Quitter");
        System.out.print("Choisissez une option: ");
    }

    private void createPost(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Entrez le titre du post: ");
        String titre = scanner.nextLine();
        System.out.print("Entrez le contenu du post: ");
        String contenu = scanner.nextLine();

        Post post = new Post();
        post.setTitre(titre);
        post.setContenu(contenu);

        postService.save(post);
        System.out.println("Post créé avec succès.");
    }

    private void readPost(Scanner scanner) {
        System.out.print("Entrez l'ID du post à récupérer: ");
        Long id = scanner.nextLong();
        Post post = postService.findById(id);
        if (post != null) {
            System.out.println("Post récupéré avec succès: " + post);
        } else {
            System.out.println("Aucun post trouvé avec cet ID.");
        }
    }

    private void updatePost(Scanner scanner) {
        System.out.print("Entrez l'ID du post à modifier: ");
        Long id = scanner.nextLong();
        Post post = postService.findById(id);
        scanner.nextLine();
        if (post != null) {
            System.out.print("Entrez le nouveau titre du post: ");
            String newTitre = scanner.nextLine();
            System.out.print("Entrez le nouveau contenu du post: ");
            String newContenu = scanner.nextLine();

            post.setTitre(newTitre);
            post.setContenu(newContenu);

            postService.save(post);
            System.out.println("Post mis à jour avec succès.");
        } else {
            System.out.println("Aucun post trouvé avec cet ID.");
        }
    }

    private void deletePost(Scanner scanner) {
        System.out.print("Entrez l'ID du post à supprimer: ");
        Long id = scanner.nextLong();
        postService.deleteById(id);
        System.out.println("Post supprimé avec succès.");
    }

    private void createCommentaire(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Entrez le contenu du commentaire: ");
        String contenu = scanner.nextLine();
        System.out.print("Entrez votre email (avec extension .net): ");
        String email = scanner.nextLine();
        System.out.print("Entrez votre nom: ");
        String nom = scanner.nextLine();
        System.out.print("Entrez l'ID du post auquel ce commentaire est associé: ");
        Long postId = scanner.nextLong();

        // Création d'un nouveau commentaire
        Commentaire commentaire = new Commentaire();
        commentaire.setContenu(contenu);
        commentaire.setEmail(email);
        commentaire.setNom(nom);

        // Appel du service pour sauvegarder le commentaire avec l'ID du post
        commentaireService.save(postId, commentaire);
        System.out.println("Commentaire créé avec succès.");
    }

    private void readAllCommentairesOfPost(Scanner scanner) {
        System.out.print("Entrez l'ID du post pour récupérer ses commentaires: ");
        Long postId = scanner.nextLong();
        List<Commentaire> commentaires = commentaireService.findAllByPostId(postId);
        if (!commentaires.isEmpty()) {
            System.out.println("Commentaires récupérés avec succès pour le post ID " + postId + ":");
            commentaires.forEach(commentaire -> System.out.println(commentaire));
        } else {
            System.out.println("Aucun commentaire trouvé pour le post ID " + postId + ".");
        }
    }

    private void readCommentaireOfPost(Scanner scanner) {
        System.out.print("Entrez l'ID du commentaire à récupérer: ");
        Long commentaireId = scanner.nextLong();
        System.out.print("Entrez l'ID du post associé au commentaire: ");
        Long postId = scanner.nextLong();

        Commentaire commentaire = commentaireService.findByIdAndPostId(commentaireId, postId);

        if (commentaire != null) {
            System.out.println("Commentaire récupéré avec succès: " + commentaire);
        } else {
            System.out.println("Aucun commentaire trouvé avec cet ID pour le post spécifié.");
        }
    }

    private void updateCommentaire(Scanner scanner) {
        System.out.print("Entrez l'ID du commentaire à modifier: ");
        Long commentaireId = scanner.nextLong();
        System.out.print("Entrez l'ID du post associé au commentaire: ");
        Long postId = scanner.nextLong();
        Commentaire commentaire = commentaireService.findByIdAndPostId(commentaireId, postId);
        scanner.nextLine();
        if (commentaire != null) {
            System.out.print("Entrez le nouveau contenu du commentaire: ");
            String newContenu = scanner.nextLine();
            commentaire.setContenu(newContenu);

            commentaireService.update(commentaireId, postId, commentaire);
            System.out.println("Commentaire mis à jour avec succès.");
        } else {
            System.out.println("Aucun commentaire trouvé avec cet ID pour le post ID spécifié.");
        }
    }

    private void deleteCommentaire(Scanner scanner) {
        System.out.print("Entrez l'ID du commentaire à supprimer: ");
        Long commentaireId = scanner.nextLong();
        System.out.print("Entrez l'ID du post associé au commentaire: ");
        Long postId = scanner.nextLong();
        commentaireService.deleteByIdAndPostId(commentaireId, postId);
        System.out.println("Commentaire supprimé avec succès.");
    }

    private void readAllPosts() {
        List<Post> posts = postService.findAll();
        if (!posts.isEmpty()) {
            System.out.println("Tous les posts récupérés avec succès:");
            posts.forEach(post -> System.out.println(post));
        } else {
            System.out.println("Aucun post trouvé.");
        }
    }

    private void readAllCommentaires() {
        List<Commentaire> commentaires = commentaireService.findAll();
        if (!commentaires.isEmpty()) {
            System.out.println("Tous les commentaires récupérés avec succès:");
            commentaires.forEach(commentaire -> System.out.println(commentaire));
        } else {
            System.out.println("Aucun commentaire trouvé.");
        }
    }
}
