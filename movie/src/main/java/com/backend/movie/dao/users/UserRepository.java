package com.backend.movie.dao.users;

import com.backend.movie.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query("SELECT EXISTS (SELECT 1 FROM UserEntity u WHERE u.login=:login)")
    boolean existByLogin(@Param("login") String login);
}
