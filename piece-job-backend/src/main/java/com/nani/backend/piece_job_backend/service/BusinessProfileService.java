package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.BusinessProfileRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessProfileService extends ProfileService{

    private BusinessProfileRepo repo;
    private SkillService skillService;
    private JwtService jwtService;
    private PJUserService userService;

    public BusinessProfileService(BusinessProfileRepo repo, SkillService skillService, JwtService jwtService, PJUserService userService) {
        this.repo = repo;
        this.skillService = skillService;
        this.jwtService = jwtService;
        this.userService = userService;
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
            exists.updateToNewInformation(business);
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

    public List<Business> getAllBusinesses() {
        return repo.findAll();
    }

    public Business getProfileFromRequestToken(HttpServletRequest request) throws Exception {
        String token = jwtService.getTokenFromRequest(request);
        System.out.println("token: "+ token );

        PJUser user = userService.getUserFromToken(token);
        return getBusinessProfileFromUserId(user.getId());
    }

}
