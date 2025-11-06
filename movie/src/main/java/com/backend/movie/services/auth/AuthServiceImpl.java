package com.backend.movie.services.auth;

import com.backend.movie.dao.users.UserRepository;
import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.models.User;
import com.backend.movie.domain.requests.LoginRequest;
import com.backend.movie.domain.requests.RefreshRequest;
import com.backend.movie.domain.requests.RegisterRequest;
import com.backend.movie.domain.response.AuthResponse;
import com.backend.movie.domain.response.TokenPairResponse;
import com.backend.movie.mappers.UserMapper;
import com.backend.movie.services.token.TokenService;
import com.backend.movie.services.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    @Override
    public AuthResponse loginUser(LoginRequest request) throws BadRequestException {
        UserEntity userEntity = userRepository.findUserByLogin(request.getLogin())
                .orElseThrow(
                        () -> new UsernameNotFoundException("Пользователь не найден")
                );
        if(!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw new BadRequestException("Неверный пароль");
        }
        return getResponse(userEntity);
    }

    @Override
    public AuthResponse registerUser(RegisterRequest request) throws BadRequestException {
        if(existsByLogin(request.getLogin())) {
            throw new BadRequestException("Логин занят");
        }
        UserEntity userEntity = this.saveNewUser(request);
        return getResponse(userEntity);
    }

    @Override
    public boolean logoutUser() {
        return true;
    }

    @Override
    public TokenPairResponse refreshSession(RefreshRequest request) {
        UUID userId = UUID.fromString(tokenService.getUserId(request.getRefreshToken()));
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Пользователь не найден")
                );
        return tokenService.getNewTokenPair(userEntity, request.getRefreshToken());
    }

    private boolean existsByLogin(String login) {
        return userRepository.existByLogin(login);
    }

    private UserEntity saveNewUser(RegisterRequest request) {
        UserEntity newUser = mapper.toUserEntity(request);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(newUser);
    }

    private AuthResponse getResponse(UserEntity user) {
        try {
            TokenPairResponse tokenPair = tokenService.getTokenPair(user);
            return new AuthResponse(tokenPair, mapper.toUser(user));
        } catch (Exception ex) {
            log.error("RECEIVED EXCEPTION " + ex.toString());
            throw ex;
        }
    }
}
