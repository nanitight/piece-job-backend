package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.PieceJobApplication;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PieceJobApplicationsDTO {
    private PieceJobDTOBasic job ;
    private List<PieceJobApplicationDTO> jobApplications  = new ArrayList<>();

    public PieceJobApplicationsDTO(PieceJob jobApplied) {
        job = new PieceJobDTO(jobApplied) ;
        extractApplications(jobApplied);
    }

    private void extractApplications(PieceJob job){
        List<PieceJobApplicationDTO> applicants = new ArrayList<>() ;
        for (PieceJobApplication jobApplication : job.getJobApplications())
            applicants.add(new PieceJobApplicationDTO(jobApplication)) ;
        System.out.println("applicants: "+applicants);
        jobApplications = applicants ;
    }
}

