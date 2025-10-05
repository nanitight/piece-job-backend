package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.Business;
import com.nani.backend.piece_job_backend.model.PJUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BusinessProfileRepoTests {

    private BusinessProfileRepo repo ;

    @Autowired
    public BusinessProfileRepoTests(BusinessProfileRepo repo) {
        this.repo = repo;
    }
    @Test
    public void BusinessProfileRepo_SaveALl_ReturnSavedBusinessProfile(){
        //Arrange
        Business user = Business.builder()
                .companyName("TestCompanyName")
                .companyAddress("Address")
                .companyRegisterNumber("RegNUmber")
                .build();

        //Act

        Business savedUser = repo.save(user) ;

        //Assert
        Assertions.assertThat(savedUser).isNotNull() ;
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0) ;
    }


}
