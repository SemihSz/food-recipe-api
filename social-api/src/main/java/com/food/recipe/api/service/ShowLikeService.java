package com.food.recipe.api.service;

import com.food.recipe.api.model.request.like.LikedBaseRequest;
import com.food.recipe.api.model.response.like.ShowLikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowLikeService {

    ShowLikeResponse showUsers(LikedBaseRequest request);
}
