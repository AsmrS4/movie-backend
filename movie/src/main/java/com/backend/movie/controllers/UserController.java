package com.backend.movie.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Эндпоинты для работы с личными данными пользователя")
public class UserController {
    @Operation(
            summary = "Получить данные о пользователе",
            description = "В ответе возвращается информация о текущем пользователе."
    )
    @GetMapping("/me")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok(null);
    }
    @Operation(
            summary = "Редактировать информацию",
            description = "В ответе возвращается обновленная информация о пользователе."
    )
    @PutMapping("/me")
    public ResponseEntity<?> editProfile() {
        return ResponseEntity.ok(null);
    }
    @Operation(
            summary = "Изменить пароль",
            description = "В ответе возвращается статус с кодом операции."
    )
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword() {
        return ResponseEntity.ok(null);
    }
}
