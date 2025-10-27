package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.PieceJobApplication;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
public class PieceJobApplicationDTO{
    private IndividualDTO jobApplicant ;
    private int id;
    private Date applicationDate ;
    private String status ;

    public PieceJobApplicationDTO( PieceJobApplication jobApplication){
        this.jobApplicant = new IndividualDTO( jobApplication.getJobApplicant());
        this.id = jobApplication.getId();
        this.applicationDate = jobApplication.getApplicationDate();
        this.status =  jobApplication.getStatus();
    }
}
