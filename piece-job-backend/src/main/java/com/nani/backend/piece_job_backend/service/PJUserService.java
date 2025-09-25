package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.dto.PJUserDTO;
import com.nani.backend.piece_job_backend.dto.PJUserUpdateDTO;
import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.repository.PJUserRepo;
import lombok.Getter;
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

    public PJUserDTO updateUser(PJUserUpdateDTO pjUser) {
        /*
        * user is changing password, not username
        * user is changing username, not password
        * user is changing both?
        * */

        PJUser oldPjUser = repo.findById(pjUser.getId()).orElse(null);
        if (oldPjUser == null) {
            throw new RuntimeException("User not found");
        }

        String updatedUsername = null;
        String updatedPassword = null;
        if (pjUser.getNewUsername() != null &&
                !oldPjUser.getUsername().equals(pjUser.getNewUsername()) &&
                !pjUser.getNewUsername().isEmpty())
        {
            //updating username
            //match to old existing username in db?
            if (!oldPjUser.getUsername().equals(pjUser.getUsername())){
                throw new RuntimeException(" old Username does not match with record of id"+
                        oldPjUser.getId()) ;
            }
            updatedUsername = pjUser.getNewUsername();
        }

        if (pjUser.getPassword() != null && pjUser.getNewPassword() != null
        && !oldPjUser.getPassword().equals(pjUser.getNewPassword()))
        {
            //updating password
            //match old existing password in db?
            String oldPassword = encoder.encode(pjUser.getPassword());
            if (!oldPjUser.getPassword().equals(oldPassword)){
                throw new RuntimeException(" old Password does not match with record of id"+
                        oldPjUser.getId());
            }
            updatedPassword = encoder.encode(pjUser.getNewPassword());
        }

        if (updatedPassword == null && updatedUsername == null){
            return new PJUserDTO(oldPjUser.getUsername(),pjUser.getLoggedInToken());
        }
        else if (updatedUsername != null && updatedPassword == null){
            //username not null
            oldPjUser.setUsername(updatedUsername) ;
            repo.save(oldPjUser);
            return new PJUserDTO(oldPjUser.getUsername(),verifyUser(oldPjUser)) ;

        }
        else if (updatedUsername == null){
            //password not null
            oldPjUser.setPassword(updatedPassword) ;
            repo.save(oldPjUser);
            return new PJUserDTO(oldPjUser.getUsername(),pjUser.getLoggedInToken());
        }
        else{
            oldPjUser.setUsername(updatedUsername);
            oldPjUser.setPassword(updatedPassword);
            repo.save(oldPjUser);
            return new PJUserDTO(oldPjUser.getUsername(),pjUser.getLoggedInToken());
        }
    }

    public List<PJUser> getAllUser() {
        return repo.findAll();
    }
}
