package com.nani.backend.piece_job_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String title;
    private String description;
    private double pay;
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name="posted_by")
    private Profile postedBy;
}
