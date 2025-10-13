package com.nani.backend.piece_job_backend.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@SuperBuilder
public abstract class Individual extends Profile{

    @ManyToMany
    @JoinTable(name = "individuals_skills_set",
            joinColumns = { @JoinColumn(name = "ind_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skillSet ;
    @ManyToMany
    @JoinTable(name = "jobs_applied_by_individual",
            joinColumns = { @JoinColumn(name = "ind_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id")})
    private List<PieceJob> jobsApplied;
    @ManyToMany
    @JoinTable(name = "jobs_completed_by_individual",
            joinColumns = { @JoinColumn(name = "ind_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id")})
    private List<PieceJob> jobsCompleted ;

    public boolean canAppliedForJob(PieceJob job){
        if (job == null)
            return false ;

        if (jobsApplied == null|| jobsApplied.isEmpty()){
            jobsApplied= new ArrayList<>();
            jobsApplied.add(job) ;
            return true ;
        }

        boolean alreadyApplied = jobsApplied.stream().anyMatch(i->
                i.getId() ==job.getId());
        if(alreadyApplied)
            return false ;
        jobsApplied.add(job) ;
        return true;
    }
}

