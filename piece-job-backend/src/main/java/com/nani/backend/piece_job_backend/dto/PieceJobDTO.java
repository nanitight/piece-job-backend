package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.Skill;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
public class PieceJobDTO  extends  PieceJobDTOBasic{
    private Date releaseDate;
    private Date expectedEndDate;
    private String specialRequirement;

    private BusinessDTO postedBy;

    public PieceJobDTO(PieceJob job){
        super(job);
        this.releaseDate = job.getReleaseDate();
        this.expectedEndDate = job.getExpectedEndDate();
        this.specialRequirement = job.getSpecialRequirement();
        this.postedBy = new BusinessDTO(job.getPostedBy()) ;
    }

}
