package com.backend.movie.services.auth;

import com.backend.movie.domain.requests.LoginRequest;
import com.backend.movie.domain.requests.RefreshRequest;
import com.backend.movie.domain.requests.RegisterRequest;
import com.backend.movie.domain.response.AuthResponse;
import com.backend.movie.domain.response.TokenPairResponse;
import org.apache.coyote.BadRequestException;

public interface AuthService {
    AuthResponse loginUser(LoginRequest request) throws BadRequestException;
    AuthResponse registerUser(RegisterRequest request) throws BadRequestException;
    boolean logoutUser();
    TokenPairResponse refreshSession(RefreshRequest request);
}
