package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.PieceJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceJobRepo extends JpaRepository<PieceJob,Integer> {
}
