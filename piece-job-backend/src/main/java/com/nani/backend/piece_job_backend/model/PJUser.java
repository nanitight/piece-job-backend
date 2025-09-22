package com.nani.backend.piece_job_backend.model;

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
}
