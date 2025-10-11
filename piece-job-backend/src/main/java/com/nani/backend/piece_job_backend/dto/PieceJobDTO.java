package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.Skill;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class PieceJobDTO {
    private int id ;
    private String title;
    private String description;
    private String location;
    private double payRate;
    private Date releaseDate;
    private Date expectedEndDate;
    private String specialRequirement;

    private List<SkillDTO> skills;
    private BusinessDTO postedBy;

    public PieceJobDTO(PieceJob job){
        this.id = job.getId() ;
        this.title = job.getTitle();
        this.description = job.getDescription();
        this.location = job.getLocation();
        this.payRate = job.getPayRate();
        this.releaseDate = job.getReleaseDate();
        this.expectedEndDate = job.getExpectedEndDate();
        this.specialRequirement = job.getSpecialRequirement();
        this.postedBy = new BusinessDTO(job.getPostedBy()) ;
        List<SkillDTO> skillDTOS = new ArrayList<>() ;
        for(Skill skill: job.getSkills() ){
            skillDTOS.add(new SkillDTO(skill));
        }
        this.skills = skillDTOS ;

    }
}
