package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.BusinessProfileRepo;
import com.nani.backend.piece_job_backend.repository.SkillRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessProfileService {

    private BusinessProfileRepo repo;
    private SkillRepo skillRepo;

    public BusinessProfileService(BusinessProfileRepo repo, SkillRepo skillRepo) {
        this.repo = repo;
        this.skillRepo = skillRepo;
    }

    public Business getBusinessProfile(int id) {
        return repo.findById(id).orElse(null) ;
    }

    public Business saveBusinessProfile(Business business) {
        List<Skill> skills = new ArrayList<>();

        for (Skill skill : business.getSkillsRequired()) {
            Skill exists = skillRepo.findSkillBySkillName(skill.getSkillName()) ;
            if (exists == null) {
                exists = skillRepo.save(skill) ;
                System.out.println("skill not found from db...");
            }
//                    .orElseGet(() -> skillRepo.save(skill) );

            if (exists.getProfiles() != null)
                exists.getProfiles().add(business);
            else {
                List<Business> user = new ArrayList<Business>();
                user.add(business);
                exists.setProfiles(user);

            }
            skills.add(exists);
        }
        business.setSkillsRequired(skills);
        System.out.println("business: "+ business+ " skills"+skills);
        return repo.save(business) ;
    }
}
