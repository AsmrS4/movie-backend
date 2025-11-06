package com.backend.movie.services.users;

import com.backend.movie.domain.models.User;
import com.backend.movie.domain.requests.EditProfileRequest;
import com.backend.movie.domain.requests.RegisterRequest;
import org.apache.coyote.BadRequestException;

import java.util.UUID;

public interface UserService {
    User getUserProfile(UUID userId);
    User editUserProfile(UUID userId, EditProfileRequest request) throws BadRequestException;
    boolean existByLogin(String login);
    void changePassword();
    User save(RegisterRequest request) throws BadRequestException;
}
