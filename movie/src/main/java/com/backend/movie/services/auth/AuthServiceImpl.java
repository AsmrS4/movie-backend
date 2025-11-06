package com.backend.movie.services.auth;

import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.models.User;
import com.backend.movie.domain.requests.LoginRequest;
import com.backend.movie.domain.requests.RefreshRequest;
import com.backend.movie.domain.requests.RegisterRequest;
import com.backend.movie.domain.response.AuthResponse;
import com.backend.movie.domain.response.TokenPairResponse;
import com.backend.movie.services.token.TokenService;
import com.backend.movie.services.users.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AuthResponse loginUser(LoginRequest request) {
        return null;
    }

    @Override
    public AuthResponse registerUser(RegisterRequest request) throws BadRequestException {
        UserDetails userDetails = userService.save(request);
        TokenPairResponse response = tokenService.getTokenPair(userDetails);
        User user = userService.getUserProfile(UUID.fromString(userDetails.getUsername()));

        return null;
    }

    @Override
    public TokenPairResponse refreshSession(RefreshRequest request) {
        return null;
    }
}
