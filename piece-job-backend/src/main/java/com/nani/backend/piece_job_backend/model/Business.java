package com.nani.backend.piece_job_backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Business extends Profile {
    @OneToMany(mappedBy = "id")
    private List<Skill> skillsRequired ;
    @OneToMany(mappedBy = "id")
    private List<Job> jobsPosted;
}
