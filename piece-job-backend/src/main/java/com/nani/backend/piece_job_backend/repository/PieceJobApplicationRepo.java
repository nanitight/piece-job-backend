package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.PieceJobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PieceJobApplicationRepo extends JpaRepository<PieceJobApplication,Integer> {
    @Query("Select a from PieceJobApplication a WHERE a.jobPosted.id = :jobId AND a.jobApplicant.id = :indId ")
    Optional<PieceJobApplication> getJobApplicationFromJobAndSeekerIds(
            @Param("jobId")int jobId,@Param("indId")int indId
    );
}
