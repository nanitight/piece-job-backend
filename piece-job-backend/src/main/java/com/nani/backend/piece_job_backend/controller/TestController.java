package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.model.Test;
import com.nani.backend.piece_job_backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TestController {
    TestService service;

    @Autowired
    public void setService(TestService service) {
        this.service = service;
    }
    @GetMapping("/")
    public ResponseEntity<List<Test>> test() {
        return new ResponseEntity<>(service.getAllTests(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Test> addTest(@RequestBody Test test) {
        return new ResponseEntity<>(service.addTest(test),HttpStatus.OK);
    }
}
