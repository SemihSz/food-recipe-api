package com.food.recipe.api.service.executable.recipe;

import com.food.recipe.api.SimpleTask;
import com.food.recipe.api.entity.recipe.RecipeEntity;
import com.food.recipe.api.mapper.RecipeEntityMapper;
import com.food.recipe.api.model.document.Base64Files;
import com.food.recipe.api.model.document.response.SaveDocumentResponse;
import com.food.recipe.api.model.input.SaveFileInput;
import com.food.recipe.api.model.input.recipe.CreateRecipeInput;
import com.food.recipe.api.model.recipe.RecipeStep;
import com.food.recipe.api.model.response.recipe.CreateRecipeResponse;
import com.food.recipe.api.repository.recipe.RecipeEntityRepository;
import com.food.recipe.api.service.executable.post.SaveFileService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateNewRecipeService implements SimpleTask<CreateRecipeInput, CreateRecipeResponse> {

  private final RecipeEntityRepository repository;

  private final SaveFileService saveFileService;

  @Override
  public CreateRecipeResponse apply(CreateRecipeInput input) {

    // Create a builder for saving a file
    final SaveFileInput.SaveFileInputBuilder builder = SaveFileInput.builder();
    builder.userId(input.getUser().getId())
        .username(input.getUser().getUsername());

    final List<RecipeStep> getRecipeList = input.getInstructions();

    if (Objects.nonNull(getRecipeList) && !getRecipeList.isEmpty()) {
      final List<RecipeStep> newRecipeStep = new ArrayList<>();
      for (RecipeStep recipeStep : getRecipeList) {
        final AtomicReference<List<Base64Files>> base64Files = new AtomicReference<>(new ArrayList<>());
        base64Files.get().add(recipeStep.getImageBase64());
        final List<SaveDocumentResponse> response = saveFileService.apply(builder.build());
        if (Objects.nonNull(response) && !response.isEmpty()) {
          final SaveDocumentResponse getSaveDocumentResponse = response.get(0);
          recipeStep.setDocumentId(getSaveDocumentResponse.getDocumentId());
          newRecipeStep.add(recipeStep);
        }
      }
      input.setInstructions(newRecipeStep);
    }
    // Call a service to save the file(s) and get a response


    final RecipeEntity recipe = RecipeEntityMapper.INSTANCE.convert(input);
    repository.save(recipe);

    return CreateRecipeResponse.builder().recipeId(recipe.getId()).build();
  }
}
