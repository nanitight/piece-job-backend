package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.service.PJUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PJUserController {
    private PJUserService service;

    public PJUserController(PJUserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String login(@RequestBody PJUser pjUser) {
        System.out.println("User "+pjUser);
        return service.verifyUser(pjUser);
    }
}

