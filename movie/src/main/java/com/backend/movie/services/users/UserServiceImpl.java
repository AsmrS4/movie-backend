package com.backend.movie.services.users;

import com.backend.movie.dao.users.UserRepository;
import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.models.User;
import com.backend.movie.domain.requests.EditProfileRequest;
import com.backend.movie.domain.requests.PasswordChangeRequest;
import com.backend.movie.domain.requests.RegisterRequest;
import com.backend.movie.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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
    public User getUserProfile(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь не найден")
        );
        return mapper.toUser(userEntity);
    }

    @Override
    public User editUserProfile(UUID userId, EditProfileRequest request) throws BadRequestException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь не найден")
        );
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
    public void changePassword(PasswordChangeRequest request) {

    }
    @Override
    public UserEntity save(RegisterRequest request) throws BadRequestException {
        if(existByLogin(request.getLogin())) {
            throw new BadRequestException("Логин занят");
        }
        UserEntity newUser = mapper.toUserEntity(request);
        return userRepository.save(newUser);
    }
    private UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
