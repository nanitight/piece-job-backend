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

    public PieceJobDTO(PieceJob realBusiness){
        this.id = realBusiness.getId() ;
        this.title = realBusiness.getTitle();
        this.description = realBusiness.getDescription();
        this.location = realBusiness.getLocation();
        this.payRate = realBusiness.getPayRate();
        this.releaseDate = realBusiness.getReleaseDate();
        this.expectedEndDate = realBusiness.getExpectedEndDate();
        this.specialRequirement = realBusiness.getSpecialRequirement();
        this.postedBy = new BusinessDTO(realBusiness.getPostedBy()) ;
        List<SkillDTO> skillDTOS = new ArrayList<>() ;
        for(Skill skill: realBusiness.getSkills() ){
            skillDTOS.add(new SkillDTO(skill));
        }
        this.skills = skillDTOS ;

    }
}
