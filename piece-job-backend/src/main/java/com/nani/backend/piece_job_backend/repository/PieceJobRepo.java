package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.PieceJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PieceJobRepo extends JpaRepository<PieceJob,Integer> {
    @Query("Select p from PieceJob p where p.postedBy.id = :busId")
    public List<PieceJob> findAllFromBusinessId(@Param("busId") int busId); //@Param("busId")int busId ) ;
}
