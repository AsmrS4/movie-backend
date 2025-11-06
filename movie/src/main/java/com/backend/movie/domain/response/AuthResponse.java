package com.backend.movie.domain.response;

import com.backend.movie.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private TokenPairResponse token;
    private User profile;
}
