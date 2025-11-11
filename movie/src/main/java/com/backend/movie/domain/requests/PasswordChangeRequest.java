package com.backend.movie.domain.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordChangeRequest {
    @NotBlank(message = "Введите предыдущий пароль")
    private String prevPassword;
    @NotBlank(message = "Введите новый пароль")
    private String newPassword;
    @NotBlank(message = "Повторите новый пароль")
    private String confirmPassword;
}
