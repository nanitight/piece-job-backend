package com.nani.backend.piece_job_backend.model.Exceptions;

import lombok.Getter;

@Getter
public class NotFoundError extends UserError {
    public NotFoundError(String message) {
        super(message);
    }
}
