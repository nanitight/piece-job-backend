package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.dto.DTOResponse;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.webauthn.api.CredentialPropertiesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillController {
    private SkillService service;

    public SkillController(SkillService skillService) {
        this.service = skillService;
    }
    @GetMapping("/skills")
    public ResponseEntity<DTOResponse<List<Skill>>> skillsRequired(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new DTOResponse<List<Skill>>(service.getSkills())
        );
    }

    @GetMapping("/skills/search")
    public ResponseEntity<DTOResponse<List<Skill>>> searchSkills(
            @RequestParam String keyword
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                new DTOResponse<>(service.getSkillFromNamePattern(keyword))
        );
    }
}
