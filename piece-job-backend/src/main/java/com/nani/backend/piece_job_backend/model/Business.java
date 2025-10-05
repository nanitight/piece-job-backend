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
@Builder
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
    private List<PieceJob> jobsPosted =new ArrayList<>();

    public void updateToNewBusinessObject(Business business) {
        this.companyName = business.getCompanyName() != null ? business.getCompanyName() : this.companyName;
        this.companyAddress = business.getCompanyAddress()  != null ? business.getCompanyAddress() : this.companyAddress;
        this.companyRegisterNumber = business.getCompanyRegisterNumber()  != null ? business.getCompanyRegisterNumber() : this.companyRegisterNumber;
        this.skillsRequired = business.getSkillsRequired() != null ? business.getSkillsRequired() : this.skillsRequired;
        this.jobsPosted = business.getJobsPosted()  != null ? business.getJobsPosted() : this.jobsPosted;
    }
}
