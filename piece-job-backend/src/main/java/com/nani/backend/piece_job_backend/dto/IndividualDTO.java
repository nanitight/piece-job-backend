package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.Individual;
import com.nani.backend.piece_job_backend.model.Profile;
import com.nani.backend.piece_job_backend.model.Skill;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class IndividualDTO extends ProfileDTO {
    private List<SkillDTO> skillSet = new ArrayList<>();

    public IndividualDTO(Individual profile) {
        super(profile);
        skillSet = new ArrayList<>() ;
        for(Skill s : profile.getSkillSet()){
            skillSet.add(new SkillDTO(s)) ;
        }
    }
}
