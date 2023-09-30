package com.food.recipe.api.controller;

import com.food.recipe.api.model.request.AuthLoginRequest;
import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.request.RegisterRequest;
import com.food.recipe.api.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RestResponse<Boolean>> registerUser(@RequestBody RegisterRequest registerRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, authService.registerUser(registerRequest)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest authRequest) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, authService.login(authRequest)));
    }

    @GetMapping("/validate")
    public ResponseEntity<RestResponse<String>> validateToken() {
        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, "Token is valid"));
    }

}
