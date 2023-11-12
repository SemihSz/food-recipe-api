package com.food.recipe.api.service.executable.like;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.post.LikeEntity;
import com.food.recipe.api.model.input.like.GetUserLikesInput;
import com.food.recipe.api.repository.post.LikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetUserLikeInfoService implements SimpleTask<GetUserLikesInput, List<LikeEntity>> {


    private final LikeRepository likeRepository;

    @Override
    public List<LikeEntity> apply(GetUserLikesInput getUserLikesInput) {

        return likeRepository.findByPost(getUserLikesInput.getPost());
    }

}
