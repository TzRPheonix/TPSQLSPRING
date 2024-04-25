package com.example.tpsqlspring.mapper;

import com.example.tpsqlspring.dto.PostDTO;
import com.example.tpsqlspring.entity.Post;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PostMapper {

    public PostDTO toDTO(Post post) {
        if (post == null) {
            return null;
        }
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitre(post.getTitre());
        dto.setContenu(post.getContenu());
        dto.setDateCreation(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        return dto;
    }

    public Post toEntity(PostDTO dto) {
        if (dto == null) {
            return null;
        }
        Post post = new Post();
        post.setId(dto.getId());
        post.setTitre(dto.getTitre());
        post.setContenu(dto.getContenu());
        return post;
    }
}
