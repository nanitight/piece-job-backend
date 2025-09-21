package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(unique = true)
    private String skillName ;

    @ManyToMany(mappedBy = "skillsRequired",cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Business> profiles;
}


