package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString(exclude = {"jobsListed", "possessedBy", "profiles" })
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

    @ManyToMany(mappedBy = "skillSet")
    @JsonIgnore
    private List<Individual> possessedBy;

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

    public void addIndividualProfileToSkill(Individual individual) {
        if (individual == null) {
            System.out.println("adding NULL individual profile to skill");
            return;
        }

        if (possessedBy != null) {
            boolean found = possessedBy.stream().anyMatch(p -> p.getId() == individual.getId());
            if (!found)
                possessedBy.add(individual);
        }
        else {
            possessedBy = new ArrayList<Individual>();;
            possessedBy.add(individual);
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


