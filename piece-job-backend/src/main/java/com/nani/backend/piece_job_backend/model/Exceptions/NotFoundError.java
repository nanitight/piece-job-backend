package com.nani.backend.piece_job_backend.model.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundError extends UserError {
    public NotFoundError(String message) {
        super(message);
        status = HttpStatus.NOT_FOUND ;
    }
}
