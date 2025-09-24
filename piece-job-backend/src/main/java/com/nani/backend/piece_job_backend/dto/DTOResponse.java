package com.nani.backend.piece_job_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DTOResponse<T> {
    private String message;
    private T data;

    public DTOResponse(String message) {
        this.message = message;
        System.out.println("DTO MSG: "+message);
    }

    public DTOResponse(T data) {
        this.data = data;
    }
}
