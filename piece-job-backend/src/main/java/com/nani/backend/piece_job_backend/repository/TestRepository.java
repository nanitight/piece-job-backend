package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

}
