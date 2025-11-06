package com.backend.movie.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/me")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok(null);
    }
    @PutMapping("/me")
    public ResponseEntity<?> editProfile() {
        return ResponseEntity.ok(null);
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword() {
        return ResponseEntity.ok(null);
    }
}
