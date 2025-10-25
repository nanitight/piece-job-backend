package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.dto.DTOResponse;
import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Exceptions.UserError;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.Seeker;
import com.nani.backend.piece_job_backend.service.PJUserService;
import com.nani.backend.piece_job_backend.service.PieceJobService;
import com.nani.backend.piece_job_backend.service.SeekerProfileService;
import com.nani.backend.piece_job_backend.service.responseFactory;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
public class SeekerController {

    private final SeekerProfileService service ;
    private final PJUserService userDetailsService;
    private final PieceJobService jobService;

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

    @GetMapping("/seeker/{id}")
    public ResponseEntity<DTOResponse<Seeker>> getASeekerFromId(@PathVariable("id") int id){
        Seeker seeker = service.getSeeker(id) ;
        if (seeker == null){
            return responseFactory.notFound("seeker with id "+id+" not found");
        }
        return ResponseEntity.ok(new DTOResponse<>(seeker)) ;
    }

    @GetMapping("/seeker-profile/{username}")
    public  ResponseEntity<DTOResponse<Seeker>> getASeekerFromUsername(
            @PathVariable("username") String username) {
        Seeker seeker = service.getSeekerFromUsername(username) ;
        if (seeker == null){
            return responseFactory.notFound("seeker with id "+username+" not found");
        }
        return ResponseEntity.ok(new DTOResponse<>(seeker)) ;
    }

    @PostMapping("/seeker/apply")
    public ResponseEntity<DTOResponse<Seeker>> applyForJob(@RequestParam("jobId") int jobId,
                     @RequestParam("indId") int indId){
        //get individual from token---
        PieceJob job = null ;
        try
        {
             job = jobService.getJobById(jobId);
        }
        catch (Exception ee){
            System.out.println("job: "+jobId+"error: "+ee.getMessage());
            return responseFactory.errorResponse("Job Not Found",HttpStatus.NOT_FOUND);
        }
        Seeker seeker = null ;
        try {
            seeker = service.getSeeker(indId) ;
        }
        catch (Exception ee){
            System.out.println("seeker: "+indId+"error: "+ee.getMessage());
            return responseFactory.errorResponse("Seeker Not Found",HttpStatus.NOT_FOUND);
        }

        try{
            System.out.println("seeker: "+seeker+" job: "+job);

            Seeker updated = service.applyForJob(seeker,job) ;
            return new ResponseEntity<DTOResponse<Seeker>>(new DTOResponse<>(updated),HttpStatus.OK) ;
        }
        catch (Exception e){
            return responseFactory.errorResponse(e);
        }


    }

    @PutMapping("/seeker/{id}")
    public ResponseEntity<DTOResponse<Seeker>> updateSeekerProfile(
            @PathVariable("id") int id, @RequestBody Seeker seeker) {

        if (seeker == null) {
            return new ResponseEntity<>(new DTOResponse<>("seeker update cannot be null"),HttpStatus.BAD_REQUEST);
        }
        else{
            try {
                return  new ResponseEntity<>(
                        new DTOResponse<>(service.updateSeekerProfile(id,seeker)),
                        HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new DTOResponse<>(e.getMessage()),HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping("/seeker/{id}")
    public ResponseEntity<DTOResponse<String>> deleteSeekerProfile(@PathVariable("id") int id)
    {
        try {
            return new ResponseEntity<>(new DTOResponse<>(
                    "deleted job seeker: "+service.deleteSeekerProfile(id)),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new DTOResponse<>(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
