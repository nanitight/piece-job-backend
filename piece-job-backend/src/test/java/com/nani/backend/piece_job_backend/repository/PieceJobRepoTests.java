package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.PieceJob;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PieceJobRepoTests {

    private PieceJobRepo repo ;

    @Autowired
    public PieceJobRepoTests(PieceJobRepo repo) {
        this.repo = repo;
    }

    @Test
    public void PieceJobRepo_SaveAJob_ReturnsNewSavedJob(){
        PieceJob  job = PieceJob.builder()
                .description("desc")
                .payRate(20)
                .title("title")
                .build();

        PieceJob savedJob = repo.save(job) ;

        Assertions.assertThat(savedJob.getId()).isGreaterThan(0);
        Assertions.assertThat(savedJob.getTitle()).isEqualTo(job.getTitle());
        Assertions.assertThat(savedJob.getDescription()).isEqualTo(job.getDescription());
        Assertions.assertThat(savedJob.getPayRate()).isEqualTo(job.getPayRate());

    }

}
