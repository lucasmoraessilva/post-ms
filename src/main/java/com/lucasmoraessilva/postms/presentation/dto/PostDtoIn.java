package com.lucasmoraessilva.postms.presentation.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDtoIn {
    private String title;
    private String summary;
    private String content;
    private Integer ownerId;
}
