package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.Seeker;
import com.nani.backend.piece_job_backend.model.Skill;
import com.nani.backend.piece_job_backend.repository.SeekerProfileRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeekerServiceTests {

    @Mock
    private SeekerProfileRepo repo;

    @Mock
    private SkillService skillService;

    @InjectMocks
    private SeekerProfileService service;

    @Test
    public void Seeker_AddingNewProfile_ReturnsASeekerProfile(){
        Seeker seeker = getASeeker() ;

        when(repo.save(Mockito.any(Seeker.class))).thenReturn(seeker);

        Seeker newSeeker = service.saveSeekerProfile(new Seeker());

        Assertions.assertThat(newSeeker).isNotNull();
        Assertions.assertThat(newSeeker.getId()).isNotZero();
        Assertions.assertThat(newSeeker.getEmail()).isEqualTo(seeker.getEmail());
    }

    public Seeker getASeeker(){
        return Seeker.builder()
                .email("1@123.com")
                .id(1)
                .build();
    }
}
