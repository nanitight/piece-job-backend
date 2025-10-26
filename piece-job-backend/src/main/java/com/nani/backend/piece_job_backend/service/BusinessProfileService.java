package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Exceptions.NotFoundError;
import com.nani.backend.piece_job_backend.model.Exceptions.UserError;
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

    public BusinessProfileService(JwtService jwtService, PJUserService userService, BusinessProfileRepo repo, SkillService skillService, JwtService jwtService1, PJUserService userService1) {
        super(jwtService, userService);
        this.repo = repo;
        this.skillService = skillService;
        this.jwtService = jwtService1;
        this.userService = userService1;
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

    public Business getBusinessProfileFromUserId(int id) throws UserError {
        String notFoundMsg = "logged in user has no business profile" ;
        return repo.getBusinessProfileFromUserId(id)
                .orElseThrow(()->new NotFoundError(notFoundMsg));
    }

    public List<Business> getAllBusinesses() {
        return repo.findAll();
    }

    public Business getProfileFromRequestToken(HttpServletRequest request) throws Exception {
        PJUser user = getUserFromRequest(request) ;
        try {
            return getBusinessProfileFromUserId(user.getId());
        }
        catch (UserError e){
            throw e ;
        }
        catch (Exception e) {
            throw new UserError(e.getMessage());
        }
    }

}
