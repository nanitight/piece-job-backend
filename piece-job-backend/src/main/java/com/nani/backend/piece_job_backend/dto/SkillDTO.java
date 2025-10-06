package com.nani.backend.piece_job_backend.dto;


import com.nani.backend.piece_job_backend.model.Skill;
import lombok.Data;

@Data
public class SkillDTO {
    private int id ;
    private String skillName ;

    public SkillDTO(Skill skill) {
        id = skill.getId();
        skillName = skill.getSkillName();
    }
}
