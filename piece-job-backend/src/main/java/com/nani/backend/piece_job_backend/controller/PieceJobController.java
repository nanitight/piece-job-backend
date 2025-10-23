package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.dto.DTOResponse;
import com.nani.backend.piece_job_backend.dto.PieceJobDTO;
import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Exceptions.ForbiddenOperation;
import com.nani.backend.piece_job_backend.model.Exceptions.NotFoundError;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.service.*;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<PieceJobDTO>> getAllJobs() {
        List<PieceJobDTO> allJobs = new ArrayList<>();
        for(PieceJob job : jobService.getJobs())
            allJobs.add(new PieceJobDTO(job));

        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @RequestMapping("/jobs/{id}")
    public ResponseEntity<PieceJobDTO> getJobById(@PathVariable("id") int id) {
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
            return responseFactory.errorResponse(e);
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
            @PathVariable("id") int id,@RequestBody PieceJob job){
        try {
            Business business = getBusinessFromRequestToken(request);
            if (job.getPostedBy() == null)
                job.setPostedBy(business);
            authoriseBusinessOnJobOperations(request,job) ;
            return new ResponseEntity<>(new DTOResponse<>(jobService.updateAJob(id,job)), HttpStatus.OK);
        }
        catch (ForbiddenOperation e){
            return responseFactory.errorResponse(e.getMessage(),HttpStatus.FORBIDDEN) ;
        }
        catch (Exception e){
            return responseFactory.errorResponse(e) ;
        }
    }

    private void authoriseBusinessOnJobOperations(HttpServletRequest request, PieceJob job) throws Exception {
        Business business = getBusinessFromRequestToken(request);
        if (business == null)
            throw new ForbiddenOperation("Only a Business can update a job") ;

        if (business.getId() != job.getPostedBy().getId()){
           throw  new ForbiddenOperation(
                    "Only a Business that posted the job can update a job") ;
        }
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<DTOResponse<String>> deleteJobById(HttpServletRequest request
            ,@PathVariable("id") int id){
        try {
            PieceJob job = jobService.getJobById(id);
            if (job == null)
                throw new NotFoundError("Job with id "+id+ "is not found") ;
            authoriseBusinessOnJobOperations(request,job);
            jobService.deleteAJob(id);
            return responseFactory.response("Job deleted");
        }
        catch (ForbiddenOperation e){
            return responseFactory.errorResponse(e.getMessage(),HttpStatus.FORBIDDEN) ;
        }
        catch (NotFoundError e){
            return responseFactory.errorResponse(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return responseFactory.errorResponse(e) ;
        }
    }
}
