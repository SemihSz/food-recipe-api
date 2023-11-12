package com.food.recipe.api.model.response.like;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShowLikeResponse {

    private List<ShowLikeUserInfo> userInfoList;
}
