package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.PJUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PJUserRepo extends JpaRepository<PJUser, Integer> {
    PJUser findByUsername(String username);

}
