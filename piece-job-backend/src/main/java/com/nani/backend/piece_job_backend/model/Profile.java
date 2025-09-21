package com.nani.backend.piece_job_backend.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne()
    @JoinColumn(name = "userId")
    private PJUser user;

}
