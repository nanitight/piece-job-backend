package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.repository.PJUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PJUserService {
    private final PJUserRepo repo;
    private AuthenticationManager authManger;
    private JwtService jwtService;
    private BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder(10);

    public PJUserService(PJUserRepo repo,AuthenticationManager  authManger,JwtService jwtService){
        this.repo = repo;
        this.authManger = authManger;
        this.jwtService = jwtService;
    }

    public PJUser register(PJUser pjUser){
        pjUser.setPassword(encoder.encode(pjUser.getPassword()));
        return repo.save(pjUser);
    }

    public PJUser getUserByUsername(String username){
        return repo.findByUsername(username);
    }

    public String verifyUser(PJUser pjUser) {
        Authentication authentication =
                authManger.authenticate( new UsernamePasswordAuthenticationToken(
                        pjUser.getUsername(), pjUser.getPassword()
                ));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(pjUser.getUsername());
        return "fail";
    }
}
