package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.BusinessProfileRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessProfileService {

    private BusinessProfileRepo repo;
    private SkillService skillService;

    public BusinessProfileService(BusinessProfileRepo repo, SkillService skillService) {
        this.repo = repo;
        this.skillService = skillService;
    }

    public Business getBusinessProfile(int id) {
        return repo.findById(id).orElse(null) ;
    }

    public Business saveBusinessProfile(Business business) {
        List<Skill> skills = skillService.getAndSaveSkillsFromBusiness(business);

        business.setSkillsRequired(skills);
        System.out.println("business: "+ business+ " skills"+skills);
        return repo.save(business) ;
    }

    public Business updateBusinessProfile(int id,Business business) {
        Business exists = repo.findById(id).orElse(null) ;
        if (exists == null) {
            throw new RuntimeException("business profile with id " + id + " not found");
        }
        else{
            business.setSkillsRequired(skillService.getAndSaveSkillsFromBusiness(business));
            exists.updateToNewBusinessObject(business);
            exists = repo.save(exists) ;
            return exists;
        }
    }

    public Business deleteBusinessProfile(int id) {
        Business exists = repo.findById(id).orElse(null) ;
        if (exists == null) {
            throw new RuntimeException("business profile with id " + id + " not found");
        }
        else{
            repo.deleteById(id);
            return exists;
        }
    }

    public Business getBusinessProfileFromUserId(int id) {
        return repo.getBusinessProfileFromUserId(id) ;
    }
}
