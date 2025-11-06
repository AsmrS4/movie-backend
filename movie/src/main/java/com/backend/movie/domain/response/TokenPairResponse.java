package com.backend.movie.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenPairResponse {
    private String accessToken;
    private String refreshToken;
}
