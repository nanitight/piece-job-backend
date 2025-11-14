package com.nani.backend.piece_job_backend.controller;

import com.nani.backend.piece_job_backend.dto.DTOResponse;
import com.nani.backend.piece_job_backend.dto.PieceJobApplicationDTO;
import com.nani.backend.piece_job_backend.model.Exceptions.NotFoundError;
import com.nani.backend.piece_job_backend.model.PieceJobApplication;
import com.nani.backend.piece_job_backend.service.PieceJobApplicationService;
import com.nani.backend.piece_job_backend.service.responseFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class PieceJobApplicationsController {
    private final PieceJobApplicationService service ;

    @PutMapping("/jobApplication/{appId}/accept")//?appId=
    public ResponseEntity<DTOResponse<PieceJobApplicationDTO>> acceptJobApplicant(
            @PathVariable("appId") int applicationId
    ) {
        try {
            PieceJobApplication application =  service.updateStatusToAccept(applicationId);
            return responseFactory.response(new PieceJobApplicationDTO(application));
        } catch (NotFoundError e) {
            return responseFactory.errorResponse(e) ;
        }
        catch (Exception e) {
            return responseFactory.errorResponse(e) ;
        }
    }

    @PutMapping("/jobApplication/{appId}/reject")//?appId=
    public ResponseEntity<DTOResponse<PieceJobApplicationDTO>> rejectJobApplicant(
            @PathVariable("appId") int applicationId
    ) {
        try {
            PieceJobApplication application =  service.updateStatusToReject(applicationId);
            return responseFactory.response(new PieceJobApplicationDTO(application));
        } catch (NotFoundError e) {
            return responseFactory.errorResponse(e) ;
        }
        catch (Exception e) {
            return responseFactory.errorResponse(e) ;
        }
    }


    @GetMapping("/jobApplication")
    public ResponseEntity<DTOResponse<List<PieceJobApplicationDTO>>> getAllApplications(){
        return responseFactory.response( service.getAllJobApplications());
    }

    @DeleteMapping("/jobApplication/{id}")
    public ResponseEntity<DTOResponse<String>> deleteJobApplication(
            @PathVariable("id") int id
    ){
        try {
            //check if requester is owner
            service.deleteApplication(id);
            return responseFactory.response("deleted successfully");
        } catch (NotFoundError e) {
            return responseFactory.errorResponse(e) ;
        }
    }
}

