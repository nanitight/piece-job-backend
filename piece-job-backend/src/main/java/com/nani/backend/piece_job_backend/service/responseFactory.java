package com.nani.backend.piece_job_backend.service;


import com.nani.backend.piece_job_backend.dto.DTOResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class responseFactory {
    /*
    * 1-with exception is Bad request
    * 1- with status for custom message only
    * */
    public static <T> ResponseEntity<DTOResponse<T>> errorResponse(Exception e){
//        System.out.println("error: \n" +e.getClass().getName()+"\n" + e.getMessage());
        return errorResponse("error: \n" +e.getClass().getName()+"\n" +
                e.getMessage(),HttpStatus.BAD_REQUEST) ;
    }
    public static <T> ResponseEntity<DTOResponse<T>> errorResponse(String message,
                              HttpStatus status){
        System.out.println("error: \n" +message);

        return new ResponseEntity<DTOResponse<T>>(new DTOResponse<T>(
                message,null
        ),HttpStatus.BAD_REQUEST) ;
    }
    public static <T> ResponseEntity<DTOResponse<T>> response(T responseObj,
                      String message){
        System.out.println("res: \n " +responseObj.getClass().getName()+"\n" + message);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new DTOResponse<T>(message,responseObj));
    }
    public static <T> ResponseEntity<DTOResponse<T>> response(T responseObj){
       return response(responseObj,"");
    }

    public static <T> ResponseEntity<DTOResponse<T>> notFound(String message){
        return errorResponse("error: \n " +message,HttpStatus.NOT_FOUND) ;
    }

}
