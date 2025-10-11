package com.nani.backend.piece_job_backend.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PJUserUpdateDTO extends PJUserDTO{
    private int id;
    private String username;
    private String newPassword;
    private String newUsername;
    private String role;
    private String employerType;
}


