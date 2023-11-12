package com.food.recipe.api.controller;

import com.food.recipe.api.model.RestResponse;
import com.food.recipe.api.model.request.like.LikedBaseRequest;
import com.food.recipe.api.model.response.like.ShowLikeResponse;
import com.food.recipe.api.service.ShowLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final ShowLikeService showLikeService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<ShowLikeResponse>> showPostLikesUsers(@RequestBody LikedBaseRequest request) {

        return ResponseEntity.ok(new RestResponse<>(HttpStatus.OK, showLikeService.showUsers(request)));
    }

}
