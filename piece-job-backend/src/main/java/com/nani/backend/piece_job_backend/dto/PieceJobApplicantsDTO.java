package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.Individual;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.PieceJobApplication;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PieceJobApplicantsDTO extends PieceJobDTOBasic{
    private List<IndividualDTO> jobApplicants  = new ArrayList<>();

    public PieceJobApplicantsDTO(PieceJob job) {
        super(job);
        extractApplicants(job);
    }

    private void extractApplicants(PieceJob job){
        List<IndividualDTO> applicants = new ArrayList<>() ;
        for (PieceJobApplication jobApplication : job.getJobApplications())
            applicants.add(new IndividualDTO(jobApplication.getJobApplicant())) ;
        System.out.println("applicants: "+applicants);
        jobApplicants = applicants ;
    }
}
