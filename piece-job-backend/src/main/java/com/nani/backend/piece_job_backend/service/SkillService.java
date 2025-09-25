package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.SkillRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {
    private SkillRepo repo ;
    public SkillService(SkillRepo repo){
        this.repo = repo;
    }

    public List<Skill> getSkills(){
        return repo.findAll();
    }

    public List<Skill> getAndSaveSkillsFromBusiness(Business business) {
        List<Skill> skills = new ArrayList<>();

        for (Skill skill : business.getSkillsRequired()) {
            Skill exists = repo.findSkillBySkillName(skill.getSkillName()) ;
            if (exists == null) {
                exists = repo.save(skill) ;
                System.out.println("skill not found from db...");
            }
//                    .orElseGet(() -> skillRepo.save(skill) );

            exists.addBusinessProfileToSkill(business);
            skills.add(exists);
        }
        return skills;
    }

    public List<Skill> getAndSaveSkillsFromPieceJob(PieceJob job) {
        List<Skill> skills = new ArrayList<>();
        if (job.getSkills() == null) {
            return skills;
        }

        for (Skill skill : job.getSkills()) {
            Skill exists = repo.findSkillBySkillName(skill.getSkillName()) ;
            if (exists == null) {

                exists = repo.save(skill) ;
                System.out.println("skill not found from db...");
            }
            Business poster = job.getPostedBy() ;
            exists.addBusinessProfileToSkill(poster);
            exists.addJobListingToSkill(job) ;
            skills.add(exists);
        }
        return skills;
    }

}
