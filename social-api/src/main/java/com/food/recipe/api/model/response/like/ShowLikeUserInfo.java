package com.food.recipe.api.model.response.like;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowLikeUserInfo {

    private String username;

    private String name;

    private Long userId;
}
