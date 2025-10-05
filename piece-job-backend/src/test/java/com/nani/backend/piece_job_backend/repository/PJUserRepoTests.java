package com.nani.backend.piece_job_backend.repository;

import com.nani.backend.piece_job_backend.model.PJUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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
        PJUser user1 = insertUserInDB(buildUser("test","12345")) ;
        PJUser user2 = buildUser("test","password") ;

        PJUser savedUser = repo.save(user2) ;

        Assertions.assertThatExceptionOfType(Exception.class) ;
    }

    @Test
    public void PJUserRepo_FindUserByUsername_ReturnsAUser(){
        String testUsername = "test" ;
        PJUser user1 = insertUserInDB(buildUser(testUsername,"12345")) ;
        PJUser findResult = repo.findByUsername(user1.getUsername()) ;

        Assertions.assertThat(findResult).isNotNull();
        Assertions.assertThat(findResult.getUsername()).isEqualTo(testUsername);
    }

    @Test
    public void PJUserRepo_GetAll_ReturnsMoreThan1Users(){
        PJUser user1 = insertUserInDB(buildUser("test1","12345")) ;
        PJUser user2 = insertUserInDB(buildUser("test2","12345")) ;

        List<PJUser> usersList = repo.findAll();

        Assertions.assertThat(usersList).isNotNull();
        Assertions.assertThat(usersList.size()).isEqualTo(2);

    }

    @Test
    public void PJUserRepo_FindById_ReturnsSavedUserById(){
        PJUser user1 = insertUserInDB(buildUser("test1","12345")) ;

        PJUser userReturned = repo.findById(user1.getId()).get();


        Assertions.assertThat(userReturned).isNotNull();
        Assertions.assertThat(userReturned.getId()).isEqualTo(user1.getId());

    }
    @Test
    public void PJUserRepo_UpdateUser_ReturnsUpdatedSavedUser(){
        PJUser user1 = insertUserInDB(buildUser("test1","12345")) ;
        String testUsernameChanged = "changedUsername" ;
        user1.setUsername(testUsernameChanged);

        PJUser userReturned = repo.save(user1);


        Assertions.assertThat(userReturned).isNotNull();
        Assertions.assertThat(userReturned.getId()).isEqualTo(user1.getId());
        Assertions.assertThat(userReturned.getUsername()).isEqualTo(testUsernameChanged);
        Assertions.assertThat(userReturned.getPassword()).isEqualTo(user1.getPassword());

    }

    @Test
    public void PJUserRepo_DeleteUser_ReturnedReviewIsEmpty(){
        PJUser user1 = insertUserInDB(buildUser("test1","12345")) ;

        repo.delete(user1);
        Optional<PJUser> userReturned = repo.findById(user1.getId()) ;

        Assertions.assertThat(userReturned).isEmpty() ;

    }

    private PJUser insertUserInDB(PJUser user){
        return repo.save(user) ;
    }

    private PJUser buildUser(String username, String password){
       return PJUser.builder()
                .username(username)
                .password(password)
                .build();
    }

}
