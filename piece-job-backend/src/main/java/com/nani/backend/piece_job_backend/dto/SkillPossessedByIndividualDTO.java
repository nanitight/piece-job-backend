package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Individual;
import com.nani.backend.piece_job_backend.model.Skill;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class SkillPossessedByIndividualDTO extends SkillDTO{
    private List<ProfileDTO> possessedBy;
    public SkillPossessedByIndividualDTO(Skill skill) {
        super(skill);
        List<ProfileDTO> possessedBy = new ArrayList<>();
        for(Individual seeker : skill.getPossessedBy()){
            possessedBy.add(new ProfileDTO(seeker)) ;
        }
        this.possessedBy = possessedBy ;
    }
}