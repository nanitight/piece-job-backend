package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer> {
}
