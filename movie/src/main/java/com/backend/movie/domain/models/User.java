package com.backend.movie.domain.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String login;
    private LocalDateTime createTime;
}
