package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.model.Profile;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.service.BusinessProfileService;
import com.nani.backend.piece_job_backend.service.PJUserDetailsService;
import com.nani.backend.piece_job_backend.service.PJUserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController//("/api/business")
public class BusinessProfileController {

    private BusinessProfileService service;
    private PJUserService userDetailsService;
    public BusinessProfileController(BusinessProfileService service, PJUserService userDetailsService) {
        this.service = service;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusinessProfile(@PathVariable String id) {
        int idNumber ;
        try {
            idNumber= Integer.parseInt(id);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Business business = service.getBusinessProfile(idNumber);
        if (business == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(business);
    }

    @PostMapping("/newprofile")
    public ResponseEntity<Business> saveBusinessProfile(@RequestBody Business business) {
        if  (business == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //add null check  on logged in user
        if (business.getSkillsRequired() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        else if(business.getSkillsRequired().size() > 0) {
//            for(Skill skill : business.getSkillsRequired()) {
//                if (skill.getProfile() != null)
//                    skill.getProfile().add(business);
//                else {
//                    List<Profile> user = new ArrayList<Profile>() ;
//                    user.add(business);
//                    skill.setProfile(user);
//                }
//            }
//        }
        System.out.println(business);
        PJUser user  = userDetailsService.getUserByUsername("b") ;
        business.setUser(user);
        return  new ResponseEntity<>(service.saveBusinessProfile(business) , HttpStatus.OK);

    }
}
