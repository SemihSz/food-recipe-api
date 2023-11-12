package com.food.recipe.api.service;

import com.food.recipe.api.entity.post.LikeEntity;
import com.food.recipe.api.entity.post.PostEntity;
import com.food.recipe.api.model.input.like.GetUserLikesInput;
import com.food.recipe.api.model.request.like.LikedBaseRequest;
import com.food.recipe.api.model.response.like.ShowLikeResponse;
import com.food.recipe.api.model.response.like.ShowLikeUserInfo;
import com.food.recipe.api.service.executable.like.GetUserLikeInfoService;
import com.food.recipe.api.service.executable.post.GetPostInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShowLikeServiceImpl implements ShowLikeService {

    private final GetPostInformationService getPostInformationService;

    private final GetUserLikeInfoService getUserLikeInfoService;

    @Override
    public ShowLikeResponse showUsers(LikedBaseRequest request) {

        final PostEntity getPostInformation = getPostInformationService.apply(request.getPostId());

        final GetUserLikesInput input = GetUserLikesInput.builder()
                .post(getPostInformation)
                .build();

        final List<LikeEntity> getUserLikeInfo = getUserLikeInfoService.apply(input);

        List<ShowLikeUserInfo> userInfoList = new ArrayList<>();

        for (LikeEntity likeEntity : getUserLikeInfo) {
            final ShowLikeUserInfo info = ShowLikeUserInfo.builder()
                    .userId(likeEntity.getUser().getId())
                    .username(likeEntity.getUser().getUsername())
                    .name(likeEntity.getUser().getName())
                    .build();
            userInfoList.add(info);
        }

        return ShowLikeResponse.builder().userInfoList(userInfoList).build();
    }
}
