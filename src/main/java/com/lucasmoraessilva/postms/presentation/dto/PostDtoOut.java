package com.lucasmoraessilva.postms.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDtoOut {
    private Integer id;
    private String title;
    private String summary;
    private String content;
    private Integer ownerId;
}
