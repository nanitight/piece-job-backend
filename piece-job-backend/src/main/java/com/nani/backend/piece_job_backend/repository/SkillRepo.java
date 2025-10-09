package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer> {
    Skill findSkillBySkillName(String skillName);

    @Query("Select s from Skill s where lower(s.skillName) "+
            "like lower(concat('%',:pattern,'%'))")
    List<Skill> findSkillThatHasPattern(@Param("pattern") String pattern);
}
