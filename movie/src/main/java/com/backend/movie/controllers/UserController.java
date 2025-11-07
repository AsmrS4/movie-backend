package com.backend.movie.controllers;

import com.backend.movie.domain.requests.EditProfileRequest;
import com.backend.movie.domain.requests.PasswordChangeRequest;
import com.backend.movie.services.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User", description = "Эндпоинты для работы с личными данными пользователя")
public class UserController {
    private final UserService userService;
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }
    @Operation(
            summary = "Получить данные о пользователе",
            description = "В ответе возвращается информация о текущем пользователе."
    )
    @GetMapping("/me")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok(userService.getUserProfile());
    }
    @Operation(
            summary = "Редактировать информацию",
            description = "В ответе возвращается обновленная информация о пользователе."
    )
    @PutMapping("/me")
    public ResponseEntity<?> editProfile(@RequestBody @Valid EditProfileRequest request) throws BadRequestException {
        return ResponseEntity.ok(userService.editUserProfile(request));
    }
    @Operation(
            summary = "Изменить пароль",
            description = "В ответе возвращается статус с кодом операции."
    )
    @PutMapping("/change/password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid PasswordChangeRequest request) throws BadRequestException {
        return ResponseEntity.ok(userService.changePassword(request));
    }
}
