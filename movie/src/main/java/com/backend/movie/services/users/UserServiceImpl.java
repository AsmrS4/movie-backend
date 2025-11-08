package com.backend.movie.services.users;

import com.backend.movie.dao.UserRepository;
import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.models.User;
import com.backend.movie.domain.requests.EditProfileRequest;
import com.backend.movie.domain.requests.PasswordChangeRequest;
import com.backend.movie.domain.requests.RegisterRequest;
import com.backend.movie.exceptions.UnauthorizedException;
import com.backend.movie.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    @Override
    public User getUserProfile() {
        UserEntity userEntity = getCurrentUser();
        return mapper.toUser(userEntity);
    }

    @Override
    public User editUserProfile( EditProfileRequest request) throws BadRequestException {
        UserEntity userEntity = getCurrentUser();

        if(existByLogin(request.getLogin()) && !Objects.equals(userEntity.getLogin(), request.getLogin())) {
            throw new BadRequestException("Логин уже занят");
        }
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setLogin(request.getLogin());

        return mapper.toUser(saveUser(userEntity));
    }
    @Override
    public boolean existByLogin(String login) {
        return userRepository.existByLogin(login);
    }
    @Override
    public boolean changePassword(PasswordChangeRequest request) throws BadRequestException {
        UserEntity userEntity = getCurrentUser();
        if(!passwordEncoder.matches(request.getPrevPassword(), userEntity.getPassword())) {
            throw new BadRequestException("Неверный пароль");
        }
        if(Objects.equals(request.getPrevPassword(), request.getNewPassword())) {
            throw new BadRequestException("Новый пароль не должен совпадать с предыдущим");
        }
        if(!Objects.equals(request.getNewPassword(), request.getConfirmPassword())) {
            throw new BadRequestException("Пароли не совпадают");
        }
        userEntity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(userEntity);
        return true;
    }
    @Override
    public UserEntity save(RegisterRequest request) throws BadRequestException {
        if(existByLogin(request.getLogin())) {
            throw new BadRequestException("Логин занят");
        }
        UserEntity newUser = mapper.toUserEntity(request);
        return userRepository.save(newUser);
    }

    private UserEntity getCurrentUser() {
        UUID userId = getUserIdFromContext();
        if(userId == null) {
            throw new UnauthorizedException("Вы не авторизованы");
        }
        return userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь не найден")
        );
    }
    private UUID getUserIdFromContext() {
        try {
            return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        } catch (Exception ex) {
            return null;
        }
    }
    private UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
