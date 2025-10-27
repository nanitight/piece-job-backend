package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class PieceJobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date applicationDate ;
    private String status = "pending" ;
    @ManyToOne
    private PieceJob jobPosted;
    @ManyToOne
    private Individual jobApplicant;

    public void applyNow(PieceJob job, Individual seeker){
        this.applicationDate = new Date() ;
        this.jobPosted = job;
        this.jobApplicant  =seeker ;
    }
}

