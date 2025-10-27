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
    private List<Skill> skillSet = new ArrayList<>();
    @OneToMany(mappedBy = "jobApplicant")
//    @JoinTable(name = "jobs_applications_by_individual",
//            joinColumns = { @JoinColumn(name = "ind_id")},
//            inverseJoinColumns = {@JoinColumn(name = "job_id")})
    private List<PieceJobApplication> jobsApplied = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "jobs_completed_by_individual",
            joinColumns = { @JoinColumn(name = "ind_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id")})
    private List<PieceJob> jobsCompleted = new ArrayList<>();

    public boolean canAppliedForJob(PieceJobApplication jobApplication){
        if (jobApplication == null)
            return false ;

        if (jobsApplied == null|| jobsApplied.isEmpty()){
            jobsApplied= new ArrayList<>();
            jobsApplied.add(jobApplication) ;
            return true ;
        }

        boolean alreadyApplied = jobsApplied.stream().anyMatch(i->
                i.getId() ==jobApplication.getId());
        if(alreadyApplied)
            return false ;
        jobsApplied.add(jobApplication) ;
        return true;
    }

    @Override
    public void updateToNewInformation(Profile profile) {
        super.updateToNewInformation(profile);
        Seeker seeker = (Seeker) profile ;
        this.skillSet = seeker.getSkillSet() != null ? seeker.getSkillSet() : this.skillSet;

    }

}

