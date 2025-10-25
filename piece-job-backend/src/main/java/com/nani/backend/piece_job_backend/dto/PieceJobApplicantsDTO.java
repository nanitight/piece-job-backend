package com.nani.backend.piece_job_backend.dto;

import com.nani.backend.piece_job_backend.model.Individual;
import lombok.Data;

import java.util.List;

@Data
public class PieceJobApplicantsDTO {
    private List<IndividualDTO> jobApplicants ;
}
