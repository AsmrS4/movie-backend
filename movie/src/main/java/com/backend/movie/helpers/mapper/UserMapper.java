package com.backend.movie.helpers.mapper;

import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.models.User;
import com.backend.movie.domain.requests.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User map(UserEntity userEntity);
    UserEntity map(RegisterRequest request);
}
