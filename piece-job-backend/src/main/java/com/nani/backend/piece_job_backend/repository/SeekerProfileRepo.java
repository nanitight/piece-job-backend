package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeekerProfileRepo extends JpaRepository<Seeker, Integer> {
    @Query("Select s from Seeker s WHERE s.user.username = :username")
    Seeker findByUsername(@Param("username") String username);
}
