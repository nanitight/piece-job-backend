package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.dto.DTOResponse;
import com.nani.backend.piece_job_backend.dto.SkillPossessedByIndividualDTO;
import com.nani.backend.piece_job_backend.dto.SkillRequiredByBusinessDTO;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SkillController {
    private SkillService service;

    public SkillController(SkillService skillService) {
        this.service = skillService;
    }
    @GetMapping("/skills")
    public ResponseEntity<DTOResponse<List<Skill>>> getAllSkills(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new DTOResponse<List<Skill>>(service.getSkills())
        );
    }

    @GetMapping("/skills/search")
    public ResponseEntity<DTOResponse<List<Skill>>> searchSkills(
            @RequestParam("keyword") String keyword
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                new DTOResponse<>(service.getSkillFromNamePattern(keyword))
        );
    }

    @GetMapping("/skillsrequired")
    public ResponseEntity<DTOResponse<List<SkillRequiredByBusinessDTO>>> getSkillsRequired(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new DTOResponse<>(service.getSkillsRequiredByBusiness())
        );
    }

    @GetMapping("/skillsposs")
    public ResponseEntity<DTOResponse<List<SkillPossessedByIndividualDTO>>> getSkillsPossessed(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new DTOResponse<>(service.getSkillsPossessedByIndividual())
        );
    }

}
