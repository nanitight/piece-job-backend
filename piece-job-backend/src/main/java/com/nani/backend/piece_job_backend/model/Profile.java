package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String firstName ="";
    private String lastName ="";
//    @Column(unique = true)
    private String email = "";

    @OneToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    protected PJUser user;

    public void setUser(PJUser user){
        this.user = user ;
        this.user.setEmployerType("individual");
        this.user.setRole("jobSeeker");
    }
    public void updateToNewInformation(Profile profile){
        this.firstName = profile.getFirstName() != null ? profile.getFirstName() : this.firstName;
        this.lastName = profile.getLastName() != null ? profile.getLastName() : this.lastName;
        this.email = profile.getEmail() != null ? profile.getEmail() : this.email;
    }

}
