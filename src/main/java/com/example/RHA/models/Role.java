package com.example.RHA.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Role {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role_user roleName;

}
