package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessProfileRepo extends JpaRepository<Business, Integer> {
    @Query("select b from Business b WHERE b.user.id = :userId ")
    public Optional<Business> getBusinessProfileFromUserId(@Param("userId")int userId);
}
