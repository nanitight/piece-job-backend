package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Exceptions.NotFoundError;
import com.nani.backend.piece_job_backend.model.Exceptions.UserError;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.model.Profile;
import jakarta.servlet.http.HttpServletRequest;


public abstract class ProfileService {
    protected final JwtService jwtService;
    private final PJUserService userService;

    public ProfileService(JwtService jwtService, PJUserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public abstract Profile getProfileFromRequestToken(HttpServletRequest request) throws Exception ;

    protected String getToken(HttpServletRequest request) throws Exception {
        String token = jwtService.getTokenFromRequest(request);
        System.out.println("token: "+ token );
        return token ;
    }

    protected PJUser getUserFromToken(String token){
        return userService.getUserFromToken(token);
    }

    protected PJUser getUserFromRequest(HttpServletRequest request) throws Exception{
        PJUser user = getUserFromToken(getToken(request)) ;
        if (user == null){
            throw new NotFoundError("no user found") ;
        }
        return user;

    }

}
