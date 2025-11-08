package com.backend.movie.services.token;

import com.backend.movie.dao.RefreshTokenRepository;
import com.backend.movie.dao.TokenRepository;
import com.backend.movie.domain.entities.RefreshTokenEntity;
import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.response.TokenPairResponse;
import com.backend.movie.exceptions.CustomJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {
    private final TokenRepository tokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.access-lifetime}")
    private Duration ACCESS_LIFETIME;
    @Value("${jwt.refresh-lifetime}")
    private Duration REFRESH_LIFETIME;
    @Transactional(rollbackOn = {Exception.class})
    public TokenPairResponse getTokenPair(UserEntity userEntity) {
        String accessToken = generateToken(userEntity, ACCESS_LIFETIME);
        String refreshToken = generateToken(userEntity, REFRESH_LIFETIME);
        try {
            deleteRefreshToken(null);
            saveRefresh(refreshToken, userEntity);
            return new TokenPairResponse(accessToken, refreshToken);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Transactional(rollbackOn = {Exception.class})
    public TokenPairResponse getNewTokenPair(UserEntity userEntity, String prevToken) {
        String newAccessToken = generateToken(userEntity, ACCESS_LIFETIME);
        String newRefreshToken = generateNewRefreshTokenByOldRefresh(userEntity, prevToken);
        try {
            deleteRefreshToken(prevToken);
            saveRefresh(newRefreshToken, userEntity);
            return new TokenPairResponse(newAccessToken, newRefreshToken);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String generateNewRefreshTokenByOldRefresh(UserEntity userEntity, String prevRefreshToken) {
        refreshTokenRepository
                .findByToken(prevRefreshToken)
                .orElseThrow(
                        () -> new CustomJwtException("Токен не найден")
                );
        if(!isTokenValid(userEntity, prevRefreshToken)) {
            throw new CustomJwtException("Невалидный токен");
        }
        return generateToken(userEntity, REFRESH_LIFETIME);
    }

    public boolean isTokenValid(UserDetails userDetails, String token) {
        String stringSessionOwnerId = getUserId(token);
        String currentUserId = userDetails.getUsername();
        return currentUserId.equals(stringSessionOwnerId) && !isTokenExpired(token);
    }

    private void deleteRefreshToken(String prevRefreshToken) {
        refreshTokenRepository
                .findByToken(prevRefreshToken)
                .ifPresent(refreshTokenRepository::delete);
    }

    private void saveRefresh(String newRefreshToken, UserEntity userEntity) {
        RefreshTokenEntity prevRefresh = refreshTokenRepository
                .findByUser(userEntity).orElse(null);
        if(prevRefresh == null) {
            RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                    .token(newRefreshToken)
                    .user(userEntity)
                    .build();
            refreshTokenRepository.save(refreshToken);
        } else {
            prevRefresh.setToken(newRefreshToken);
            refreshTokenRepository.save(prevRefresh);
        }
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpiration(token);
        return expirationDate.before(new Date());
    }

    private String generateToken(UserEntity userEntity, Duration lifeTime) {
        Date issuedAt = new Date();
        Date expiredAt = new Date(issuedAt.getTime() + lifeTime.toMillis());
        return Jwts.builder()
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .setId(String.valueOf(userEntity.getId()))
                .setSubject(userEntity.getLogin())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String getUserId(String token) {
        return getClaims(token).getId();
    }

    private Date getExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    private Claims getClaims(@NotNull String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            throw new CustomJwtException(ex.getMessage());
        }
    }
}
