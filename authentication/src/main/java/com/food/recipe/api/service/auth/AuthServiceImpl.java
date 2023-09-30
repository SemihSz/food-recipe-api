package com.food.recipe.api.service.auth;

import com.food.recipe.api.model.request.AuthLoginRequest;
import com.food.recipe.api.model.request.RegisterRequest;
import com.food.recipe.api.model.response.JwtResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 30.09.2023
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public Boolean registerUser(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public JwtResponse login(AuthLoginRequest authLoginRequest) {
        return null;
    }
}
