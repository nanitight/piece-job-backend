package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(unique = true)
    private String skillName ;

    @ManyToMany(mappedBy = "skillsRequired")
    @JsonIgnore
    private List<Business> profiles = new ArrayList<>();

    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    private List<PieceJob> jobsListed ;

    public void addBusinessProfileToSkill(Business business) {
        if (business == null) {
            System.out.println("adding NULL business profile to skill");
            return;
        }

        if (profiles != null) {
            boolean found = profiles.stream().anyMatch(p -> p.getId() == business.getId());
            if (!found)
                profiles.add(business);
        }
        else {
            profiles = new ArrayList<Business>();;
            profiles.add(business);
        }

    }

    public void addJobListingToSkill(PieceJob job) {
        if (jobsListed != null){
            boolean found = jobsListed.stream().anyMatch(j -> j.getId() == job.getId());
            if (!found)
                jobsListed.add(job);
        }
        else {
            jobsListed = new ArrayList<>();
            jobsListed.add(job);
        }
    }
}


