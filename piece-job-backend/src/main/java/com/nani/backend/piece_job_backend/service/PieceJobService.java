package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.PieceJobRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceJobService {
    PieceJobRepo repo ;
    SkillService skillService ;

    public PieceJobService(PieceJobRepo repo, SkillService skillService) {
        this.repo = repo;
        this.skillService = skillService;
    }

    public List<PieceJob> getJobs(){
        return repo.findAll();
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

    public void deleteAJob(int id) {
        repo.deleteById(id);
    }
}
