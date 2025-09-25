package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessProfileRepo extends JpaRepository<Business, Integer> {
    @Query("select b from Business b WHERE b.user.id = :userId ")
    public Business getBusinessProfileFromUserId(int userId);
}
