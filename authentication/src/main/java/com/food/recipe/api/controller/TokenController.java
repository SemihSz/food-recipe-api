package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    @GetMapping("/validate")
    public ResponseEntity<RestResponse<String>> validateToken() {
        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, "Token is valid"));
    }

}
