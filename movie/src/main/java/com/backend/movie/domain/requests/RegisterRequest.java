package com.backend.movie.domain.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @Size(max = 50, message = "Допустимая длина имени до 50 символов")
    private String firstName;
    @Size(max = 50, message = "Допустимая длина фамилии до 50 символов")
    private String lastName;
    @NotBlank(message = "Логин не может быть пустым")
    @Size(min = 3, max = 50, message = "Допустимая длина логина от 3 до 50 символов")
    private String login;
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 20, message = "Допустимая длина пароля от 8 до 20 символов")
    private String password;
}
