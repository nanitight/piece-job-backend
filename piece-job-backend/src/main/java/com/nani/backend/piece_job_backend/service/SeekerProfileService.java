package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Seeker;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.SeekerProfileRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class SeekerProfileService {

    private final SkillService skillService;
    private SeekerProfileRepo repo;

    public Seeker saveSeekerProfile(Seeker seeker) {
        List<Skill> skills = skillService.getAndSaveSkillsFromIndividual(seeker);
        seeker.setSkillSet(skills);
        System.out.println("seeker: "+ seeker+ " skills"+skills);
        return repo.save(seeker) ;
    }

    public List<Seeker> getAllIndividuals() {
        return repo.findAll() ;
    }
}
