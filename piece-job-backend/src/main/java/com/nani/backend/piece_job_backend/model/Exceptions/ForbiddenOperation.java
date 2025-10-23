package com.nani.backend.piece_job_backend.model.Exceptions;

import lombok.Getter;

@Getter
public class ForbiddenOperation extends UserError {
    public ForbiddenOperation(String message) {
        super(message);
    }
}
