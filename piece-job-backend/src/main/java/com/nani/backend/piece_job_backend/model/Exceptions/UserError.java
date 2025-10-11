package com.nani.backend.piece_job_backend.model.Exceptions;


public class UserError extends Exception {
    private String message ;
    public UserError(String msg){
        this.message = msg ;
    }
}
