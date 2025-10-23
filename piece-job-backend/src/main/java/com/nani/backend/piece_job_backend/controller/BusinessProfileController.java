package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.dto.DTOResponse;
import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.service.BusinessProfileService;
import com.nani.backend.piece_job_backend.service.responseFactory;
import com.nani.backend.piece_job_backend.service.PJUserService;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController//("/api/business")
@CrossOrigin
public class BusinessProfileController {

    private BusinessProfileService service;
    private PJUserService userDetailsService;


    public BusinessProfileController(BusinessProfileService service,
            PJUserService userDetailsService) {
        this.service = service;
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/business")
    public ResponseEntity<DTOResponse<List<Business>>> getAllBusinessProfiles(){
        return ResponseEntity.ok().body(new DTOResponse<List<Business>>(service.getAllBusinesses()));
    }

    @GetMapping("business/{id}")
    public ResponseEntity<DTOResponse<Business>> getBusinessProfile(@PathVariable("id") String id) {
        int idNumber ;
        try {
            idNumber= Integer.parseInt(id);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new DTOResponse<>(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
        Business business = service.getBusinessProfile(idNumber);
        if (business == null)
            return responseFactory.notFound("business with id "+idNumber+" not found");
        return ResponseEntity.ok(new DTOResponse<>(business));
    }

    @PostMapping("/newprofile")
    public ResponseEntity<DTOResponse<Business>> saveBusinessProfile(HttpServletRequest request,
                        @RequestBody Business business) {
        if (business == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (business.getSkillsRequired() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //add null check  on logged-in user
        try {
            String token =  userDetailsService.getJwtService().getTokenFromRequest(request);
//            System.out.println("token: "+ token );

            PJUser user = userDetailsService.getUserFromToken(token);
            business.setUser(user);
//            System.out.println("business: "+ business+ " user"+user);
            return ResponseEntity.ok(new DTOResponse<>(service.saveBusinessProfile(business)));
        }
        catch (DataAccessException | SecurityException e){
            return responseFactory.errorResponse(e) ;
        }
        catch (Exception e) {
            System.out.println("error: \n"+e.getClass().getName()+"\n"+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/business/{id}")
    public ResponseEntity<DTOResponse<Business>> updateBusinessProfile(@PathVariable("id") int id,
                       @RequestBody Business business) {

        if (business == null) {
            return new ResponseEntity<>(new DTOResponse<>("business update cannot be null"),HttpStatus.BAD_REQUEST);
        }
        else{
            try {
                return  new ResponseEntity<>(
                        new DTOResponse<>(service.updateBusinessProfile(id,business)),
                        HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new DTOResponse<>(e.getMessage()),HttpStatus.BAD_REQUEST);
            }
        }
    }

    @DeleteMapping("/business/{id}")
    public ResponseEntity<DTOResponse<Business>> deleteBusinessProfile(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(new DTOResponse<>(
                    "deleted business: "+service.deleteBusinessProfile(id)),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new DTOResponse<>(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
