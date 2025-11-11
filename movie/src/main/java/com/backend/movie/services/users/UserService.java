package com.backend.movie.services.users;

import com.backend.movie.domain.models.User;
import com.backend.movie.domain.requests.EditProfileRequest;
import com.backend.movie.domain.requests.PasswordChangeRequest;
import org.apache.coyote.BadRequestException;

public interface UserService {
    User getUserProfile();
    User editUserProfile(EditProfileRequest request) throws BadRequestException;
    boolean existByLogin(String login);
    boolean changePassword(PasswordChangeRequest request) throws BadRequestException;
}
