package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.Individual;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.PieceJobApplication;
import com.nani.backend.piece_job_backend.model.Seeker;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class IndividualSeekerDTO extends IndividualDTO{
    private List<PieceJobApplicationDTO> jobsApplied;

    public IndividualSeekerDTO(Seeker profile) {
        super(profile);
        extractJobApplications(profile);
    }

    private void extractJobApplications(Seeker seeker){
        this.jobsApplied = new ArrayList<>() ;
        for (PieceJobApplication jobApplication : seeker.getJobsApplied())
            jobsApplied.add(new PieceJobApplicationDTO(jobApplication)) ;
    }
}
