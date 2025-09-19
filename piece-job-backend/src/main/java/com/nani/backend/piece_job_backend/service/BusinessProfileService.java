package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.repository.BusinessProfileRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusinessProfileService {

    private BusinessProfileRepo repo;

    public BusinessProfileService(BusinessProfileRepo repo) {
        this.repo = repo;
    }

    public Business getBusinessProfile(int id) {
        return repo.findById(id).orElse(null) ;
    }

    public Business saveBusinessProfile(Business business) {
        return repo.save(business) ;
    }
}
