package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.PJUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class PJUserDTO {
    private String username;
    private String password;
    private String loggedInToken;
    protected String role;
    protected String employerType;

    public PJUserDTO(String username) {
        this.username = username;
    }

    public PJUserDTO(String username, String loggedInToken) {
        this.username = username;
        this.loggedInToken = loggedInToken;
    }
    public PJUserDTO(PJUser user, String loggedInToken) {
        this.username = user.getUsername();
        this.role = user.getRole();
        this.employerType = user.getEmployerType();
        this.loggedInToken = loggedInToken;
    }
}
