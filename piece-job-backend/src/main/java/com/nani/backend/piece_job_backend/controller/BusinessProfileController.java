package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.model.Profile;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.SkillRepo;
import com.nani.backend.piece_job_backend.service.BusinessProfileService;
import com.nani.backend.piece_job_backend.service.JwtService;
import com.nani.backend.piece_job_backend.service.PJUserDetailsService;
import com.nani.backend.piece_job_backend.service.PJUserService;
import jakarta.servlet.http.HttpServletRequest;
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
    private JwtService jwtService;


    public BusinessProfileController(BusinessProfileService service,
            PJUserService userDetailsService, JwtService jwtService) {
        this.service = service;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
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
    public ResponseEntity<Business> saveBusinessProfile(HttpServletRequest request, @RequestBody Business business) {
        if (business == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (business.getSkillsRequired() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //add null check  on logged in user
        try {
            String tokenUserName =  jwtService.extractUsername(jwtService.getTokenFromRequest(request));
            System.out.println("token: "+ tokenUserName );

            PJUser user = userDetailsService.getUserByUsername(tokenUserName);
            business.setUser(user);
            System.out.println("business: "+ business+ " user"+user);
            return new ResponseEntity<>(service.saveBusinessProfile(business), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        }
    }
