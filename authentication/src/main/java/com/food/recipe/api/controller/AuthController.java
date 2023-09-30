package com.food.recipe.api.controller;

import com.food.recipe.api.model.request.AuthLoginRequest;
import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.request.RegisterRequest;
import com.food.recipe.api.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/v1/register")
    public ResponseEntity<RestResponse<Boolean>> registerUser(@RequestBody RegisterRequest registerRequest) {

        return ResponseEntity.ok(new RestResponse<>(200, authService.registerUser(registerRequest)));
    }

    @PostMapping(value = "/v1/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginRequest authRequest) {

        return ResponseEntity.ok(new RestResponse<>(200, authService.login(authRequest)));
    }

    @GetMapping("/v1/validate")
    public String validateToken() {
        return "Token is valid";
    }

}
