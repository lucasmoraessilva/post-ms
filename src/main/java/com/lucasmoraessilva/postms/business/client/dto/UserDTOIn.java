package com.lucasmoraessilva.postms.business.client.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOIn {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthdate;
}
