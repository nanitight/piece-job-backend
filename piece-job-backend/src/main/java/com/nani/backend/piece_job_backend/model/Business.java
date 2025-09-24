package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Business extends Profile {
    @Column(unique = true)
    private String companyName;
    private String companyAddress;
    private String companyRegisterNumber;

    @ManyToMany
    @JoinTable(name="profile_skills", joinColumns = {@JoinColumn(name="p_id")},
        inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skillsRequired ;
    @OneToMany(mappedBy = "postedBy")
    private List<Job> jobsPosted =new ArrayList<>();

    public void updateToNewBusinessObject(Business business) {
        this.companyName = business.getCompanyName();
        this.companyAddress = business.getCompanyAddress();
        this.companyRegisterNumber = business.getCompanyRegisterNumber();
        this.skillsRequired = business.getSkillsRequired();
        this.jobsPosted = business.getJobsPosted();
    }
}
