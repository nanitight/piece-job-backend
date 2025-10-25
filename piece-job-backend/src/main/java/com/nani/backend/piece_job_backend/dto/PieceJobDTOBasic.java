package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.Skill;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PieceJobDTOBasic {
    private int id ;
    private String title;
    private List<SkillDTO> skills;
    private String description;
    private String location;
    private double payRate;

    public PieceJobDTOBasic(PieceJob job){
        this.id = job.getId() ;
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.location = job.getLocation();
        this.payRate = job.getPayRate();
        extractSkillsDTO(job);
    }

    protected void extractSkillsDTO(PieceJob job){
        List<SkillDTO> skillDTOS = new ArrayList<>() ;
        for(Skill skill: job.getSkills() ){
            skillDTOS.add(new SkillDTO(skill));
        }
        this.skills = skillDTOS ;
    }
}
