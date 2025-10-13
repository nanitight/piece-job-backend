package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Seeker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SeekerProfileRepoTests {

    SeekerProfileRepo repo ;

    @Autowired
    public SeekerProfileRepoTests(SeekerProfileRepo repo) {
        this.repo = repo;
    }

    @Test
    public void SeekerRepo_SaveNewSeeker_ReturnsSeeker(){
        Seeker seeker = Seeker.builder()
                .email("1@123.com")
                .lastName("lastname")
                .firstName("Name")
                .build();
        Seeker saved = repo.save(seeker) ;

        Assertions.assertThat(saved.getId()).isGreaterThan(0);
        Assertions.assertThat(saved.getEmail()).isEqualTo(seeker.getEmail());

    }

}
