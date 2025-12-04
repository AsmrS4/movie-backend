package com.backend.movie.domain.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditReviewRequest {
    private UUID id;
    @NotBlank(message = "Поле комментария обязательно к заполнению")
    @Size(max = 2000, message = "Максимально допустимая длина текста - 2000 символов")
    private String comment;
    @Min(value = 1, message = "Минимальная допустимая оценка: 1")
    @Max(value = 10, message = "Максимальная допустимая оценка: 10")
    private int rating;
    private boolean isAnonymous;
}
