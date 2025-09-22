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
    @ManyToMany
    @JoinTable(name="profile_skills", joinColumns = {@JoinColumn(name="p_id")},
        inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skillsRequired ;
    @OneToMany(mappedBy = "postedBy")
    private List<Job> jobsPosted =new ArrayList<>();
}
