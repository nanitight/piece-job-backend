package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessProfileRepo extends JpaRepository<Business, Integer> {
}
