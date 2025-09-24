package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.dto.DTOResponse;
import com.nani.backend.piece_job_backend.dto.PJUserDTO;
import com.nani.backend.piece_job_backend.dto.PJUserUpdateDTO;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.service.responseFactory;
import com.nani.backend.piece_job_backend.service.PJUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PJUserController {
    private PJUserService service;

    public PJUserController(PJUserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<DTOResponse<PJUserDTO>> login(@RequestBody PJUser pjUser) {
        System.out.println("User "+pjUser);
        try{
            String token = service.verifyUser(pjUser);
            PJUserDTO pjUserDTO = new PJUserDTO(pjUser.getUsername(), token);
            return responseFactory.response(pjUserDTO) ;
        }
        catch (Exception e){
            return responseFactory.errorResponse(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<DTOResponse<PJUserDTO>> register(@RequestBody PJUser pjUser) {
        if (pjUser.getPassword() == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        String password = pjUser.getPassword();
        try{
            PJUser savedUSer = service.register(pjUser);
            if  (savedUSer == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        String token = service.verifyUser(new PJUser(savedUSer.getId(), pjUser.getUsername(),pjUser.getPassword()));
            String token = service.verifyUser(new PJUser(savedUSer.getUsername(), password)) ;
            PJUserDTO userRegistering = new PJUserDTO(savedUSer.getUsername(), token);

            return responseFactory.response(userRegistering) ;
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<DTOResponse<PJUserDTO>>(new DTOResponse<PJUserDTO>(
                    e.getMessage(),new PJUserDTO(pjUser.getUsername())), HttpStatus.BAD_REQUEST);
        }

        catch (Exception e){
            return responseFactory.errorResponse(e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<DTOResponse<PJUserDTO>> updatePassword(HttpServletRequest request,
                                        @RequestBody PJUserUpdateDTO pjUser) {
        try{
            String token =  service.getJwtService().getTokenFromRequest(request);
            pjUser.setLoggedInToken(token);
            System.out.println("request: "+ pjUser );
            //expect pjUser with correct Id
            PJUserDTO updatedPjUser = service.updateUser(pjUser);
            System.out.println("updated user: "+ updatedPjUser);

            PJUserDTO pjUserDTO = new PJUserDTO(updatedPjUser.getUsername(), token);
            return new ResponseEntity<>(new DTOResponse<>(pjUserDTO), HttpStatus.OK);

        }
        catch (Exception e) {
            System.out.println( "error" + e.getMessage());
            return new ResponseEntity<>(new DTOResponse<>(e.getMessage()),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<DTOResponse<List<PJUser>>> getAllUsers() {
        return ResponseEntity.ok(new DTOResponse<>(service.getAllUser()));
    }
}

