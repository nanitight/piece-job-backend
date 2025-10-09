package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.dto.DTOResponse;
import com.nani.backend.piece_job_backend.dto.PieceJobDTO;
import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.service.*;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PieceJobController {
    PieceJobService jobService;
    JwtService jwtService;
    BusinessProfileService businessService;
    PJUserService userService;

    public PieceJobController(PieceJobService jobService, JwtService jwtService,
              BusinessProfileService businessService, PJUserService userService) {
        this.jobService = jobService;
        this.jwtService = jwtService;
        this.businessService = businessService;
        this.userService = userService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<PieceJob>> getAllJobs() {
        return new ResponseEntity<>(jobService.getJobs(), HttpStatus.OK);
    }

    @RequestMapping("/jobs/{id}")
    public ResponseEntity<PieceJobDTO> getJobById(@PathVariable int id) {
        PieceJob job = jobService.getJobById(id);
        if (job != null)
            return new ResponseEntity<>(new PieceJobDTO(job),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/jobs")
    public ResponseEntity<DTOResponse<PieceJob>> addJob(HttpServletRequest request, @RequestBody PieceJob job){
        try {
            Business business = getBusinessFromRequestToken(request);
            System.out.println("business: "+ business  + " job: "+ job);
            if (business == null)
                return responseFactory.errorResponse("Only a Business can post a job",HttpStatus.FORBIDDEN) ;
            job.setPostedBy(business);
            return ResponseEntity.ok(new DTOResponse<>(jobService.addJob(job)));
        }
        catch (DataAccessException | SecurityException e){
            return responseFactory.errorResponse(e) ;
        }
        catch (Exception e) {
            System.out.println("error: \n"+e.getClass().getName()+"\n"+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private Business getBusinessFromRequestToken(HttpServletRequest request) throws Exception {
        String token = jwtService.getTokenFromRequest(request);
        System.out.println("token: "+ token );

        PJUser user = userService.getUserFromToken(token);
        return businessService.getBusinessProfileFromUserId(user.getId());
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<DTOResponse<PieceJob>> updateAJob(HttpServletRequest request,
            @PathVariable int id,@RequestBody PieceJob job){
        try {
            Business business = getBusinessFromRequestToken(request);
            if (business == null)
                return responseFactory.errorResponse("Only a Business can update a job",HttpStatus.FORBIDDEN) ;
            if (job.getPostedBy() == null)
                job.setPostedBy(business);
            if (business.getId() != job.getPostedBy().getId()){
                    return responseFactory.errorResponse(
                            "Only a Business that posted the job can update a job",HttpStatus.FORBIDDEN) ;
            }
            return new ResponseEntity<>(new DTOResponse<>(jobService.updateAJob(id,job)), HttpStatus.OK);
        }
        catch (Exception e){
            return responseFactory.errorResponse(e) ;
        }
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteJobById(@PathVariable int id){
        jobService.deleteAJob(id) ;
    }
}
