package com.backend.movie.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShort {
    private UUID id;
    private String login;
    private String imageUrl;
}
