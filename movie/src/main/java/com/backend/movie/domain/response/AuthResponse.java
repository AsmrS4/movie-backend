package com.backend.movie.domain.response;

import com.backend.movie.domain.models.User;

public class AuthResponse {
    private TokenPairResponse token;
    private User profile;
}
