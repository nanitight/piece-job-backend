package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.dto.SkillInJobsDTO;
import com.nani.backend.piece_job_backend.dto.SkillPossessedByIndividualDTO;
import com.nani.backend.piece_job_backend.dto.SkillRequiredByBusinessDTO;
import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Individual;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.SkillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {
    private SkillRepo repo ;
    @Autowired
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

    public List<Skill> getAndSaveSkillsFromIndividual(Individual individual) {
        List<Skill> skills = new ArrayList<>();

        for (Skill skill : individual.getSkillSet()) {
            Skill exists = repo.findSkillBySkillName(skill.getSkillName()) ;
            if (exists == null) {
                exists = repo.save(skill) ;
                System.out.println("skill not found from db...");
            }
//                    .orElseGet(() -> skillRepo.save(skill) );

            exists.addIndividualProfileToSkill(individual);
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

    public List<Skill> getSkillFromNamePattern(String skillName){

        return repo.findSkillThatHasPattern(skillName) ;
    }

    public List<SkillRequiredByBusinessDTO> getSkillsRequiredByBusiness() {
        List<SkillRequiredByBusinessDTO> skills = new ArrayList<>();
        for (Skill skill : getSkills()){
            skills.add(new SkillRequiredByBusinessDTO(skill));
        }
        return skills;
    }

    public List<SkillPossessedByIndividualDTO> getSkillsPossessedByIndividual(){
        List<SkillPossessedByIndividualDTO> skills = new ArrayList<>();
        for (Skill skill : getSkills()){
            skills.add(new SkillPossessedByIndividualDTO(skill));
        }
        return skills;
    }

    public List<SkillInJobsDTO> getSkillsAndTheirJobListing() {
        List<SkillInJobsDTO> skills = new ArrayList<>();
        for (Skill skill : getSkills()){
            skills.add(new SkillInJobsDTO(skill));
        }
        return skills;
    }
}
