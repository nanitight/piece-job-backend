package com.nani.backend.piece_job_backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class Individual extends Profile{

    @OneToMany(mappedBy = "profile")
    private List<Skill> skillSet ;
    @OneToMany(mappedBy = "id")
    private List<Job> jobsAccepted ;
    @OneToMany(mappedBy = "id")
    private List<Job> jobsCompleted ;
}

