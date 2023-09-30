package com.food.recipe.api.model.request;

import com.food.recipe.api.enums.RoleTypes;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 30.09.2023
 */
@Getter
@Setter
public class RegisterRequest {

    private String username;

    private String password;

    private String email;

    private RoleTypes role;

}
