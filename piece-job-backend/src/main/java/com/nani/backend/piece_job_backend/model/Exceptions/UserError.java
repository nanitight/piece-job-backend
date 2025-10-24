package com.nani.backend.piece_job_backend.model.Exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserError extends Exception {
    private final String message ;
    protected HttpStatus status;
    public UserError(String msg){
        this.message = msg ;
        this.status = HttpStatus.BAD_REQUEST;
    }

}
