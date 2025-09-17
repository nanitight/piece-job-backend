package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Test;
import com.nani.backend.piece_job_backend.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository repo;


//    public TestService(TestRepository repo) {
//        this.repo = repo;
//    }

    public List<Test> getAllTests() {
        return repo.findAll();
    }

    public Test addTest(Test test) {
        return repo.save(test);
    }
}
