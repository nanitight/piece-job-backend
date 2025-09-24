package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.PJUser;
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
    private String password;
    private String loggedInToken;

    public PJUserDTO(String username) {
        this.username = username;
    }

    public PJUserDTO(String username, String loggedInToken) {
        this.username = username;
        this.loggedInToken = loggedInToken;
    }
}
