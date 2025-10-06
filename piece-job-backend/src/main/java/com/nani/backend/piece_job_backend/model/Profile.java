package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nani.backend.piece_job_backend.dto.PJUserDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String firstName;
    private String lastName;
//    @Column(unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private PJUser user;

}
