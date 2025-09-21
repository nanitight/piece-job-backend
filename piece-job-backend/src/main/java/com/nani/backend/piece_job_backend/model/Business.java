package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Business extends Profile {
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="profile_skills", joinColumns = {@JoinColumn(name="p_id")},
        inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skillsRequired ;
    @OneToMany(mappedBy = "postedBy")
    private List<Job> jobsPosted;
}
