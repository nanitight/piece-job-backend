package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.service.BusinessProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("/api/business")
public class BusinessProfileController {

    private BusinessProfileService service;

    public BusinessProfileController() {
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
        return  new ResponseEntity<>(service.saveBusinessProfile(business) , HttpStatus.OK);

    }
}
