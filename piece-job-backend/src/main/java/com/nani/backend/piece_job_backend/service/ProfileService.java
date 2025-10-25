package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Profile;
import jakarta.servlet.http.HttpServletRequest;

public abstract class ProfileService {
    public abstract Profile getProfileFromRequestToken(HttpServletRequest request) throws Exception ;
}
