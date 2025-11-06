package com.backend.movie.services.token;

import com.backend.movie.dao.auth.RefreshTokenRepository;
import com.backend.movie.dao.auth.TokenRepository;
import com.backend.movie.domain.entities.RefreshTokenEntity;
import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.response.TokenPairResponse;
import com.backend.movie.exceptions.CustomJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {
    private TokenRepository tokenRepository;
    private RefreshTokenRepository refreshTokenRepository;
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.access-lifetime}")
    private Duration ACCESS_LIFETIME;
    @Value("${jwt.refresh-lifetime}")
    private Duration REFRESH_LIFETIME;
    @Transactional(rollbackOn = {Exception.class})
    public TokenPairResponse getTokenPair(UserDetails userDetails) {
        String accessToken = generateToken(userDetails, ACCESS_LIFETIME);
        String refreshToken = generateToken(userDetails, REFRESH_LIFETIME);
        try {
            deleteRefreshToken(null);
            saveRefresh(refreshToken, userDetails);
            return new TokenPairResponse(accessToken, refreshToken);
        } catch (Exception ex) {
            throw new RuntimeException("Что-то пошло не так");
        }
    }

    @Transactional(rollbackOn = {Exception.class})
    public TokenPairResponse getNewTokenPair(UserDetails userDetails, String prevToken) {
        String newAccessToken = generateToken(userDetails, ACCESS_LIFETIME);
        String newRefreshToken = generateNewRefreshTokenByOldRefresh(userDetails, prevToken);
        try {
            deleteRefreshToken(prevToken);
            saveRefresh(newRefreshToken, userDetails);
            return new TokenPairResponse(newAccessToken, newRefreshToken);
        } catch (Exception ex) {
            throw new RuntimeException("Что-то пошло не так");
        }
    }

    private String generateNewRefreshTokenByOldRefresh(UserDetails userDetails, String prevRefreshToken) {
        RefreshTokenEntity refreshToken = refreshTokenRepository
                .findByToken(prevRefreshToken)
                .orElseThrow(
                        () -> new CustomJwtException("Токен не найден")
                );
        if(!isTokenValid(userDetails, prevRefreshToken)) {
            throw new CustomJwtException("Невалидный токен");
        }
        return generateToken(userDetails, REFRESH_LIFETIME);
    }

    public boolean isTokenValid(UserDetails userDetails, String token) {
        String stringSessionOwnerId = getUserId(token);
        String currentUserId = userDetails.getUsername();
        return currentUserId.equals(stringSessionOwnerId) && !isTokenExpired(token);
    }

    private void deleteRefreshToken(String prevRefreshToken) {
        refreshTokenRepository
                .findByToken(prevRefreshToken)
                .ifPresent(refreshToken -> refreshTokenRepository.delete(refreshToken));
    }

    private void saveRefresh(String newRefreshToken, UserDetails userDetails) {
        RefreshTokenEntity refreshToken = RefreshTokenEntity.builder()
                .token(newRefreshToken)
                .user((UserEntity) userDetails)
                .build();
        refreshTokenRepository.save(refreshToken);
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpiration(token);
        return expirationDate.before(new Date());
    }

    private String generateToken(UserDetails userDetails, Duration lifeTime) {
        Date issuedAt = new Date();
        Date expiredAt = new Date(issuedAt.getTime() + lifeTime.toMillis());
        return Jwts.builder()
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .setId(userDetails.getUsername())
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
                    .parseClaimsJwt(token)
                    .getBody();
        } catch (Exception ex) {
            throw new CustomJwtException(ex.getMessage());
        }
    }
}
