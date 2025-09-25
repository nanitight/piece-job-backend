package com.nani.backend.piece_job_backend.model;


import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class Individual extends Profile{

    @OneToMany(mappedBy = "profile")
    private List<Skill> skillSet ;
    @OneToMany(mappedBy = "id")
    private List<PieceJob> jobsAccepted ;
    @OneToMany(mappedBy = "id")
    private List<PieceJob> jobsCompleted ;
}

