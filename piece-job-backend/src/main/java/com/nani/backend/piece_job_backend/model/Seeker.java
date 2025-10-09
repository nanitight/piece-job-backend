package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Seeker extends Individual {
    //has a business. If they want to Post Jobs as well.
    @Override
    public void setUser(PJUser user){
        this.user = user ;
        this.user.setEmployerType("individual");
        this.user.setRole("jobSeeker");
    }
}
