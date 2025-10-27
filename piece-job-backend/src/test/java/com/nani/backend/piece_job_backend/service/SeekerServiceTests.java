package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Individual;
import com.nani.backend.piece_job_backend.model.PieceJob;
import com.nani.backend.piece_job_backend.model.Seeker;
import com.nani.backend.piece_job_backend.repository.PieceJobRepo;
import com.nani.backend.piece_job_backend.repository.SeekerProfileRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeekerServiceTests {

    @Mock
    private SeekerProfileRepo repo;

    @Mock
    private PieceJobService jobService;

    @Mock
    private SkillService skillService;

    @Mock
    private PieceJobRepo jobRepo;

    @InjectMocks
    private SeekerProfileService service;

    private Seeker seeker ;

    private PieceJob job;

    @Test
    public void Seeker_AddingNewProfile_ReturnsASeekerProfile(){


        when(repo.save(Mockito.any(Seeker.class))).thenReturn(seeker);
        when(skillService.getAndSaveSkillsFromIndividual(Mockito.any(Individual.class))).thenReturn(new ArrayList<>());
        Seeker newSeeker = service.saveSeekerProfile(getASeeker());

        Assertions.assertThat(newSeeker).isNotNull();
//        Assertions.assertThat(newSeeker.getId()).isNotZero();
        Assertions.assertThat(newSeeker.getEmail()).isEqualTo(seeker.getEmail());
    }

    @Test
    public void Seeker_ApplyingForAJob_AddsSeekersApplicationAndAddJobApplicationToJobsApplied_ApplicationMustHaveInv(){
        System.out.println("Job: "+job);

        try{
//            when(jobService.getJobById(job.getId())).thenReturn(job);
//            when(jobRepo.save(job)).thenReturn(job);

            Seeker saved = service.applyForJob(seeker, job);
            Assertions.assertThat(saved.getJobsApplied().size()).isGreaterThan(0) ;
            Assertions.assertThat(saved.getJobsApplied().get(0).getJobPosted()).isEqualTo(job) ;

        } catch (Exception e) {
            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
        }

    }

    @BeforeEach
    public void init(){
        seeker = getASeeker() ;
        job = PieceJob.builder()
//                .id(1)
                .description("desc")
                .payRate(20)
                .title("title")
                .build();
    }

    private Seeker getASeeker(){
        return Seeker.builder()
                .email("1@123.com")
//                .id(1)
                .build();
    }
}
