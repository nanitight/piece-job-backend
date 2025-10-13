package com.nani.backend.piece_job_backend.model.Exceptions;


import lombok.Getter;

@Getter
public class UserError extends Exception {
    private final String message ;
    public UserError(String msg){
        this.message = msg ;
    }

}
