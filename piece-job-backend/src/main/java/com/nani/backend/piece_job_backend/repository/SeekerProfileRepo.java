package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeekerProfileRepo extends JpaRepository<Seeker, Integer> {
}
