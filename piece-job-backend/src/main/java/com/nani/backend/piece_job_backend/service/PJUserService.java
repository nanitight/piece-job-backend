package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.dto.PJUserDTO;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.repository.PJUserRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PJUserService {
    private final PJUserRepo repo;
    private AuthenticationManager authManger;
    @Getter
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

    public PJUser getUserFromToken(String token){
        String tokenUserName = jwtService.extractUsername(token);
        System.out.println("userName: "+ tokenUserName );

        return getUserByUsername(tokenUserName);
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

    public PJUser updateUser(PJUser pjUser) {
        return repo.save(pjUser);
    }

    public List<PJUser> getAllUser() {
        return repo.findAll();
    }
}
