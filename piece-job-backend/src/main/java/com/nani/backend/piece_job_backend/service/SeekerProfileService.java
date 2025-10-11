package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Exceptions.UserError;
import com.nani.backend.piece_job_backend.model.Exceptions.NotFoundError;
import com.nani.backend.piece_job_backend.model.Individual;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.Seeker;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.SeekerProfileRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class SeekerProfileService {

    private final SkillService skillService;
    private final SeekerProfileRepo repo;
    private final PieceJobService jobService;

    public Seeker saveSeekerProfile(Seeker seeker) {
        List<Skill> skills = skillService.getAndSaveSkillsFromIndividual(seeker);
        seeker.setSkillSet(skills);
        System.out.println("seeker: "+ seeker+ " skills"+skills);
        return repo.save(seeker) ;
    }

    public List<Seeker> getAllIndividuals() {
        return repo.findAll() ;
    }

    public void applyForJob(Individual applicant, int jobId) throws UserError {
        if (applicant == null) {
            System.out.println("Applicant is null, wtf");
            throw new UserError("Could not find applicant");
        }
        PieceJob job = null ;
        try
        {
           job = jobService.getJobById(jobId);
        }
        catch (Exception ee){
            throw new UserError(ee.getMessage());
        }
        if (job == null){
            //non-existent job
            throw new NotFoundError("Can't find Job with id "+jobId) ;
        }
        else{
            if (!job.applyForJob(applicant)){
                throw new UserError("Already applied for the job");
            }
        }
    }
}
