package com.backend.movie.controllers;

import com.backend.movie.domain.requests.LoginRequest;
import com.backend.movie.domain.requests.RefreshRequest;
import com.backend.movie.domain.requests.RegisterRequest;
import com.backend.movie.services.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authorization", description = "Эндпоинты для регистрации, авторизации и обновления сессии пользователя")
public class AuthController {
    private final AuthService authService;
    public AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Авторизоваться в системе",
            description = "В ответе возвращается пара access токена и refresh токена, а также информация о пользователе."
    )
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest request) throws BadRequestException {
        return ResponseEntity.ok(authService.loginUser(request));
    }
    @Operation(
            summary = "Создать учетную запись",
            description = "В ответе возвращается пара access токена и refresh токена, а также информация о созданном пользователе."
    )
    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequest request) throws BadRequestException {
        return ResponseEntity.ok(authService.registerUser(request));
    }
    @Operation(
            summary = "Выйти из системы",
            description = "В ответе возвращается статус с кодом операции."
    )
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok(authService.logoutUser());
    }
    @Operation(
            summary = "Получить новую пару токенов",
            description = "В ответе возвращается новая пара пара access токена и refresh токена."
    )
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshSession(@RequestBody @Valid RefreshRequest request) {
        return ResponseEntity.ok(authService.refreshSession(request));
    }
}
