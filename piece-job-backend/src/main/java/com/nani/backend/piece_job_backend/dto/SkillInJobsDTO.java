package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.Skill;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SkillInJobsDTO extends SkillDTO {
    private List<PieceJobDTO> jobsListed = new ArrayList<>() ;
    public SkillInJobsDTO(Skill skill){
        super(skill);
        for(PieceJob job : skill.getJobsListed()){
            jobsListed.add(new PieceJobDTO(job)) ;
        }
    }
}
