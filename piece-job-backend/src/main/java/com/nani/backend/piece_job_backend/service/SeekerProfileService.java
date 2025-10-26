package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.*;
import com.nani.backend.piece_job_backend.model.Exceptions.UserError;
import com.nani.backend.piece_job_backend.model.Exceptions.NotFoundError;
import com.nani.backend.piece_job_backend.repository.SeekerProfileRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeekerProfileService extends ProfileService{

    private final SkillService skillService;
    private final SeekerProfileRepo repo;

    public SeekerProfileService(JwtService jwtService, PJUserService userService, SkillService skillService, SeekerProfileRepo repo) {
        super(jwtService, userService);
        this.skillService = skillService;
        this.repo = repo;
    }

    public Seeker saveSeekerProfile(Seeker seeker) {
        List<Skill> skills = skillService.getAndSaveSkillsFromIndividual(seeker);
        seeker.setSkillSet(skills);
        System.out.println("seeker: "+ seeker+ " skills"+skills);
        return repo.save(seeker) ;
    }

    public Seeker getSeeker(int id){
        return repo.findById(id).orElse(null);
    }


    public List<Seeker> getAllIndividuals() {
        return repo.findAll() ;
    }

    public Seeker applyForJob(Seeker applicant, PieceJob job) throws UserError {
        if (applicant == null) {
            System.out.println("Applicant is null, wtf");
            throw new UserError("Could not find applicant");
        }
        if (job == null){
            //non-existent job
            throw new NotFoundError("Job cannot be null") ;
        }
        else{
            if (!job.applyForJob(applicant)){
                throw new UserError("Already applied for the job");
            }
            //user-has not already applied?
            if (!applicant.canAppliedForJob(job)){
                throw new UserError("Already applied for the job");
            }
            Seeker saved = repo.save(applicant) ;
            return saved ;

        }
    }

    public Seeker getSeekerFromUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }

    public Seeker updateSeekerProfile(int id, Seeker seeker) {
        Seeker exists = repo.findById(id).orElse(null) ;
        if (exists == null) {
            throw new RuntimeException("seeker profile with id " + id + " not found");
        }
        else{
            seeker.setSkillSet(skillService.getAndSaveSkillsFromIndividual(seeker));
            exists.updateToNewInformation(seeker);
            exists = repo.save(exists) ;
            return exists;
        }
    }

    public Seeker deleteSeekerProfile(int id) {
        Seeker exists = repo.findById(id).orElse(null) ;
        if (exists == null) {
            throw new RuntimeException("business profile with id " + id + " not found");
        }
        else{
            repo.deleteById(id);
            return exists;
        }
    }

    public Seeker getSeekerProfileFromUserId(int id) throws UserError{
        String notFoundMessage = "logged in user has no seeker profile" ;
        return repo.findByUserId(id).orElseThrow(()->new NotFoundError(notFoundMessage)) ;
    }

    @Override
    public Seeker getProfileFromRequestToken(HttpServletRequest request) throws Exception {
        PJUser user = getUserFromRequest(request) ;
        try {
            return getSeekerProfileFromUserId(user.getId());
        }
        catch (UserError e){
            throw e ;
        }
        catch (Exception e) {
            throw new UserError(e.getMessage());
        }
    }
}
