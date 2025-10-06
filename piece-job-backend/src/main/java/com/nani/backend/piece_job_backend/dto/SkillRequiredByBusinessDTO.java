package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Skill;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SkillRequiredByBusinessDTO extends SkillDTO{
    private List<BusinessDTO> profiles ;
    public SkillRequiredByBusinessDTO(Skill skill) {
        super(skill);
        List<BusinessDTO> requiredby = new ArrayList<>();
        for(Business business : skill.getProfiles()){
            requiredby.add(new BusinessDTO(business)) ;
        }
        this.profiles = requiredby ;
    }
}
