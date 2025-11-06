package com.backend.movie.domain.requests;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private String prevPassword;
    private String newPassword;
    private String confirmPassword;
}
