package example.recipes.mappers;

import example.recipes.db.model.RecipeDescriptionEntity;
import example.recipes.db.model.UserRecipeEntity;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRecipesMapper {

    public UserRecipeEntity mapUserRecipeToNewEntity(AddUserRecipeRequestDto recipeRequestDto) {
        return new UserRecipeEntity(
                recipeRequestDto.getUserId(),
                recipeRequestDto.getRecipeName(),
                ZonedDateTime.now()
        );
    }

    public RecipeDescriptionEntity mapRecipeDescriptionToNewEntity(AddUserRecipeRequestDto recipeRequestDto) {
        return new RecipeDescriptionEntity(
                recipeRequestDto.getRecipeName(),
                recipeRequestDto.getUserId(),
                recipeRequestDto.getRecipeInstructions(),
                recipeRequestDto.getIsVegetarian(),
                recipeRequestDto.getServingsNumber(),
                StringUtils.join(recipeRequestDto.getIngredients(), ",")
        );
    }

    public UserRecipeEntity updateRecipeEntity(ChangeRecipeRequestDto recipeRequestDto, UserRecipeEntity recipesEntity) {
        Optional<RecipeDescriptionEntity> recipeEntity = recipesEntity.getRecipeDescriptions().stream().filter(it -> it.getRecipeName().equals(recipeRequestDto.getOldRecipeName())).findFirst();

        if (recipeEntity.isPresent()) {
            RecipeDescriptionEntity entity = recipeEntity.get();
            recipesEntity.setRecipeDescriptions(Collections.singletonList(new RecipeDescriptionEntity(
                    (recipeRequestDto.getNewRecipeName() == null ? entity.getRecipeName() : recipeRequestDto.getNewRecipeName()),
                    recipeRequestDto.getUserId() == null ? entity.getUserId() : recipeRequestDto.getUserId(),
                    recipeRequestDto.getRecipeInstructions() == null ? entity.getRecipeInstructions() : recipeRequestDto.getRecipeInstructions(),
                    recipeRequestDto.getIsVegetarian() == null ? entity.getIsVegetarian() : recipeRequestDto.getIsVegetarian(),
                    recipeRequestDto.getServingsNumber() == null ? entity.getServingsNumber() : recipeRequestDto.getServingsNumber(),
                    recipeRequestDto.getIngredients() == null ? entity.getIngredients() : StringUtils.join(recipeRequestDto.getIngredients(), ","))
            ));
        }
        return recipesEntity;
    }
}
