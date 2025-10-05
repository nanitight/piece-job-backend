package com.nani.backend.piece_job_backend.service;

import com.nani.backend.piece_job_backend.model.PJUser;
import com.nani.backend.piece_job_backend.repository.PJUserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PJUserServiceTests {

    @Mock
    private PJUserRepo repo;

    @InjectMocks
    private PJUserService service;

    @Test
    public void PJUserService_CreateUser_ReturnsPJUserLoggedDTO(){
        PJUser user = PJUser.builder()
                .username("test1")
                .password("12345")
                .build();

        when(repo.save(Mockito.any(PJUser.class))).thenReturn(user) ;

        PJUser returnedUser = service.register(user) ;

        Assertions.assertThat(returnedUser).isNotNull() ;
        Assertions.assertThat(returnedUser).isNotNull() ;

    }

    @Test
    public void PJUserService_GetAllUsers_ReturnsListOfUsers(){
        List<PJUser> usersReturn = Mockito.mock(List.class) ;

        when(repo.findAll()).thenReturn(usersReturn);

        List<PJUser> returnedUsers = service.getAllUser() ;

        Assertions.assertThat(returnedUsers).isNotNull();

    }


}
