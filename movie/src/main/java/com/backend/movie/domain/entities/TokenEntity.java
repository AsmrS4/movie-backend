package com.backend.movie.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

@Data
@Entity
@Table(name = "tokens")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    private boolean isExpired;
    private boolean isRevoked;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
