package com.nani.backend.piece_job_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PJUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;

    public PJUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public PJUser(int id) {
        this.id = id;
    }
}
