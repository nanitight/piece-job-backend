package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.dto.PJUserDTO;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.service.PJUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public ResponseEntity<PJUserDTO> register(@RequestBody PJUser pjUser) {
        if (pjUser.getPassword() == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        PJUser savedUSer = service.register(pjUser);
        if  (savedUSer == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        PJUserDTO userRegistering = new PJUserDTO(savedUSer.getUsername());

        return new ResponseEntity<>(userRegistering, HttpStatus.OK);
    }
}

