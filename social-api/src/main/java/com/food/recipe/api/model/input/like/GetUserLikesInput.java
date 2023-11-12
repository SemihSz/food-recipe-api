package com.food.recipe.api.model.input.like;

import com.food.recipe.api.entity.post.PostEntity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetUserLikesInput {

    private PostEntity post;
}
