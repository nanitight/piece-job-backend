package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.dto.DTOResponse;
import com.nani.backend.piece_job_backend.dto.PieceJobApplicationDTO;
import com.nani.backend.piece_job_backend.model.Exceptions.NotFoundError;
import com.nani.backend.piece_job_backend.model.Exceptions.UserError;
import com.nani.backend.piece_job_backend.model.Individual;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.PieceJobApplication;
import com.nani.backend.piece_job_backend.repository.PieceJobApplicationRepo;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteApplication(int id) throws NotFoundError{
        PieceJobApplication application = repo.findById(id).orElseThrow(
                ()-> new NotFoundError("Application for piece job with id "+id+"is not found"));

        application.setJobPosted(null);
        application.setJobApplicant(null); //send notification of job unavailable

        repo.deleteById(id);

    }

    public PieceJobApplication getApplication(int applicationId) throws NotFoundError{
        return repo.findById(applicationId).orElseThrow(
                ()-> new NotFoundError("Application for piece job with id "+applicationId+"is not found"));

    }

    public List<PieceJobApplicationDTO> getAllJobApplications() {
        return repo.findAll().stream().map(PieceJobApplicationDTO::new).collect(Collectors.toList());
    }

    public PieceJobApplication updateStatusToAccept(int applicationId) throws NotFoundError{
        PieceJobApplication application = getApplication(applicationId) ;
        application.setStatus("accepted");
        return repo.save(application);
    }



    public PieceJobApplication updateStatusToReject(int applicationId) throws NotFoundError {
        PieceJobApplication application = getApplication(applicationId) ;
        application.setStatus("rejected");
        return repo.save(application);
    }
}
