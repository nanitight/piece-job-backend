package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.Profile;
import lombok.Data;

@Data
public class ProfileDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public ProfileDTO(Profile profile){
        this.id = profile.getId();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.email = profile.getEmail();
    }
}
