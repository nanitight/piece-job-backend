package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.SkillRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private SkillRepo repo ;
    public SkillService(SkillRepo repo){
        this.repo = repo;
    }

    public List<Skill> getSkills(){
        return repo.findAll();
    }
}
