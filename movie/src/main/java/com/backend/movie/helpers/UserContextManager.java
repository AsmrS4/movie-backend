package com.backend.movie.helpers;

import com.backend.movie.dao.UserRepository;
import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.exceptions.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class UserContextManager {
    public static UserEntity getUserFromContext(UserRepository userRepository) {
        try {
            UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            return userRepository.getReferenceById(userId);
        } catch (Exception ex) {
            log.error("RECEIVED EXCEPTION: " + ex);
            throw new UnauthorizedException("Вы не авторизованы");
        }
    }
}
