package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.Business;
import lombok.Data;

@Data
public class BusinessDTO {
    private int id;
    private String companyName;
    private String companyAddress;
    private String companyRegisterNumber;

    public BusinessDTO(Business postedBy) {
        this.companyAddress = postedBy.getCompanyAddress();
        this.companyName = postedBy.getCompanyName();
        this.companyRegisterNumber = postedBy.getCompanyRegisterNumber() ;
        this.id = postedBy.getId() ;
    }
}
