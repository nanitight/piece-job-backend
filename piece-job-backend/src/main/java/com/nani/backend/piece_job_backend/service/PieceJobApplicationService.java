package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Exceptions.UserError;
import com.nani.backend.piece_job_backend.model.Individual;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.PieceJobApplication;
import com.nani.backend.piece_job_backend.repository.PieceJobApplicationRepo;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class PieceJobApplicationService {
    private final PieceJobApplicationRepo repo ;
    public PieceJobApplication apply(PieceJob job, Individual seeker) throws UserError {
        PieceJobApplication application = repo.getJobApplicationFromJobAndSeekerIds(
                job.getId(),seeker.getId() ).orElse(null)  ;

        if (application != null)
            throw new UserError("Already applied for the job");

        application = new PieceJobApplication();
        application.applyNow(job,seeker);
        return repo.save(application) ;
    }

    public void deleteApplication(int id){
        PieceJobApplication application = repo.findById(id).orElse(null) ;
        if (application == null) {
            return;
        }
        application.setJobPosted(null);
        application.setJobApplicant(null);

        repo.deleteById(id);

    }
}
