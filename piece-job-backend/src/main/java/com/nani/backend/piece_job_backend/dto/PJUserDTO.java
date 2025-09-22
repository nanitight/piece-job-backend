package com.nani.backend.piece_job_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PJUserDTO {
    private String username;
    private String loggedInToken;

    public PJUserDTO(String username) {
        this.username = username;
    }
}
