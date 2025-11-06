package com.backend.movie.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<?> loginUser() {
        return ResponseEntity.ok(null);
    }
    @PostMapping("/registration")
    public ResponseEntity<?> registerUser() {
        return ResponseEntity.ok(null);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshSession() {
        return ResponseEntity.ok(null);
    }
}
