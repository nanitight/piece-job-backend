package com.nani.backend.piece_job_backend.model.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ForbiddenOperation extends UserError {
    public ForbiddenOperation(String message) {
        super(message);
        status = HttpStatus.FORBIDDEN ;
    }
}
