package com.backend.movie.domain.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshRequest {
    @NotBlank(message = "Поле не может быть пустым")
    private String refreshToken;
}
