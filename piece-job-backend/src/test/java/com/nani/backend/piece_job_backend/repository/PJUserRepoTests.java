package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.PJUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PJUserRepoTests {
    @Autowired
    private PJUserRepo repo;

    @Test
    public void PJUserRepo_SaveALl_ReturnSavedPJUser(){
        //Arrange
        PJUser user = PJUser.builder()
                .username("test")
                .password("1234")
                .build();
        //Act

        PJUser savedUser = repo.save(user) ;

        //Assert
        Assertions.assertThat(savedUser).isNotNull() ;
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0) ;
    }

    @Test
    public void PJUserRepo_SaveDuplicateNames_ThrowsException(){
        PJUser user1 = insertUserInDB(buidUser("test","12345")) ;
        PJUser user2 = buidUser("test","password") ;

        PJUser savedUser = repo.save(user2) ;

        Assertions.assertThatExceptionOfType(Exception.class) ;d
    }

    private PJUser insertUserInDB(PJUser user){
        return repo.save(user) ;
    }

    private PJUser buidUser(String username,String password){
       return PJUser.builder()
                .username(username)
                .password(password)
                .build();
    }

}
