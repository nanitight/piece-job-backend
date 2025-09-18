package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.model.PJUserPrincipal;
import com.nani.backend.piece_job_backend.repository.PJUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PJUserDetailsService implements UserDetailsService {

    private PJUserRepo userRepo;

    public PJUserDetailsService(PJUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PJUser user = userRepo.findByUsername(username) ;
        if  (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new PJUserPrincipal(user);
    }


}
