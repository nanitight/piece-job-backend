package com.nani.backend.piece_job_backend.service;


import com.nani.backend.piece_job_backend.dto.DTOResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class responseFactory {
    public static <T> ResponseEntity<DTOResponse<T>> errorResponse(Exception e){
        System.out.println("error: \n" +e.getClass().getName()+"\n" + e.getMessage());

        return new ResponseEntity<DTOResponse<T>>(new DTOResponse<T>(
                e.getMessage(),null
        ),HttpStatus.BAD_REQUEST) ;
    }
    public static <T> ResponseEntity<DTOResponse<T>> response(T responseObj,
                      String message){
        System.out.println("res: \n" +responseObj.getClass().getName()+"\n" + message);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new DTOResponse<T>(message,responseObj));
    }
    public static <T> ResponseEntity<DTOResponse<T>> response(T responseObj){
       return response(responseObj,"");
    }

}
