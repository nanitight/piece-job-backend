package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.dto.PieceJobApplicationsDTO;
import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Exceptions.NotFoundError;
import com.nani.backend.piece_job_backend.model.Exceptions.UserError;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.PieceJobApplication;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.PieceJobRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceJobService {
    PieceJobRepo repo ;
    SkillService skillService ;
    PieceJobApplicationService applicationService;

    public PieceJobService(PieceJobRepo repo, SkillService skillService,
                           PieceJobApplicationService applicationService) {
        this.repo = repo;
        this.skillService = skillService;
        this.applicationService = applicationService;
    }

    public List<PieceJob> getJobs(){
        return repo.findAll();
    }
    public List<PieceJob> getJobs(Business byBusiness) throws Exception{
        List<PieceJob> allJobsByBuss = repo.findAllFromBusinessId(byBusiness.getId()).orElse(null);
        if (allJobsByBuss == null)
            throw new UserError("something went wrong");
        return allJobsByBuss;
    }

    public PieceJob getJobById(int id){
        return repo.findById(id).orElse(null) ;
    }

    public PieceJob addJob(PieceJob job){
        List<Skill>  skills = skillService.getAndSaveSkillsFromPieceJob(job) ;
        job.setSkills(skills);
        System.out.println("Job: "+ job+ " skills"+skills);

        return repo.save(job) ;
    }

    public PieceJob updateAJob(int id, PieceJob job) {
        PieceJob exists = repo.findById(id).orElse(null) ;
        if  (exists == null) {
            throw new RuntimeException("Job with id " + id + " not found");
        }
        job.setSkills(skillService.getAndSaveSkillsFromPieceJob(job));
        exists.updateToNewPieceJobInformation(job) ;
        exists = repo.save(exists) ;
        return exists;
    }

    public void deleteAJob(int id) throws NotFoundError {
        PieceJob job = repo.findById(id).orElse(null) ;
        if (job == null)
            throw new NotFoundError("Job with id "+id+ " not found") ;

        if (job.getJobApplications() != null && !job.getJobApplications().isEmpty()){
            for (PieceJobApplication s : job.getJobApplications()){
                try {
                    applicationService.deleteApplication(s.getId());
                } catch (NotFoundError _) {
                    System.out.println("Tried deleting job application, but didn't find, id: "+s.getId());
                }
            }
        }

        repo.deleteById(id);
    }

    public PieceJobApplicationsDTO getApplicantsFromJob(int id) throws NotFoundError{
        PieceJob job = getJobById(id) ;
        if (job == null){
            System.out.println("job with id "+id+" is not found");
            throw new NotFoundError("job with id "+id+" is not found") ;
        }
        return new PieceJobApplicationsDTO(job);

    }
}
