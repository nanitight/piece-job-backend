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
    private PJUserRepo userRepo;
    @Autowired
    public BusinessProfileRepoTests(BusinessProfileRepo repo,PJUserRepo userRepo) {
        this.repo = repo;
        this.userRepo = userRepo ;
    }
    @Test
    public void BusinessProfileRepo_SaveALl_ReturnSavedBusinessProfile(){
        //Arrange
        Business business = Business.builder()
                .companyName("TestCompanyName")
                .companyAddress("Address")
                .companyRegisterNumber("RegNUmber")
                .build();

        //Act

        Business savedBusiness = repo.save(business) ;

        //Assert
        Assertions.assertThat(savedBusiness).isNotNull() ;
        Assertions.assertThat(savedBusiness.getId()).isGreaterThan(0) ;
    }

    @Test
    public void BusinessRepo_GetBusinessProfileThatExist_ReturnsBusinessEntity(){
        PJUser owner = PJUser.builder().build() ;
        owner = userRepo.save(owner) ;
        int userId = owner.getId();
//        System.out.println("saved owner: "+owner);
        Business business = Business.builder()
                .companyName("TestCompanyName")
                .companyAddress("Address")
                .companyRegisterNumber("RegNUmber")
                .user(owner)
                .build() ;
//        System.out.println("Test saved business: "+business);
        Business insert = repo.save(business) ;
        Business saved = repo.getBusinessProfileFromUserId(userId) ;

        Assertions.assertThat(saved).isNotNull() ;
        Assertions.assertThat(saved.getId()).isGreaterThan(0) ;

        Assertions.assertThat(saved.getUser().getId()).isEqualTo(userId) ;
    }


}
