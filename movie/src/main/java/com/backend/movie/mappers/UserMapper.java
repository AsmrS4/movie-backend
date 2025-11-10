package com.backend.movie.mappers;

import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.models.User;
import com.backend.movie.domain.models.UserShort;
import com.backend.movie.domain.requests.RegisterRequest;
import com.backend.movie.services.users.UserService;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    User toUser(UserEntity userEntity);
    UserEntity toUserEntity(RegisterRequest request);
    UserShort toShortUser(UserEntity user);
}
