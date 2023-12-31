package com.food.recipe.api.service;

import com.food.recipe.api.model.request.like.LikeRecipeRequest;
import com.food.recipe.api.model.request.recipe.CreateRecipeRequest;
import com.food.recipe.api.model.request.recipe.DeleteRecipeRequest;
import com.food.recipe.api.model.request.recipe.UpdateRecipeRequest;
import com.food.recipe.api.model.response.recipe.CreateRecipeResponse;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService extends LikeDislikeService {

    CreateRecipeResponse createNewRecipe(CreateRecipeRequest request);

    CreateRecipeResponse updatedRecipe(UpdateRecipeRequest request);

    Boolean deleteRecipe(DeleteRecipeRequest request);

    void commentRecipe();
}
