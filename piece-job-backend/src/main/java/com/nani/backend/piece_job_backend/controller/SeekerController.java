package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.dto.DTOResponse;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.model.Seeker;
import com.nani.backend.piece_job_backend.service.PJUserService;
import com.nani.backend.piece_job_backend.service.SeekerProfileService;
import com.nani.backend.piece_job_backend.service.responseFactory;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class SeekerController {

    private final SeekerProfileService service ;
    private PJUserService userDetailsService;

    @PostMapping("seeker/newprofile")
    public ResponseEntity<DTOResponse<Seeker>> createSeeker(HttpServletRequest request, @RequestBody Seeker seeker){
        //if user is already a business? is it legal.
        //add null check  on logged-in user
        try {
            String token =  userDetailsService.getJwtService().getTokenFromRequest(request);
            PJUser user = userDetailsService.getUserFromToken(token);
            seeker.setUser(user);
            return ResponseEntity.ok(new DTOResponse<>(service.saveSeekerProfile(seeker)));
        }
        catch (DataAccessException | SecurityException e){
            return responseFactory.errorResponse(e) ;
        }
        catch (Exception e) {
            System.out.println("error: \n"+e.getClass().getName()+"\n"+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/seeker")
    public ResponseEntity<DTOResponse<List<Seeker>>> getAllIndividualSeekers(){
        return ResponseEntity.ok(new DTOResponse<>(service.getAllIndividuals())) ;
    }

}
