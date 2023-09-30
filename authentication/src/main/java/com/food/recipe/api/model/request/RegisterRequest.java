package com.food.recipe.api.model.request;

import com.food.recipe.api.enums.RoleTypes;
import com.food.recipe.api.validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 30.09.2023
 */
@Getter
@Setter
public class RegisterRequest {

    private String username;

    @ValidPassword
    private String password;

    private String email;

    private RoleTypes role;

}
