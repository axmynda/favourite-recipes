package example.recipes.mappers;

import example.recipes.db.model.RecipeDescriptionEntity;
import example.recipes.db.model.UserRecipeEntity;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
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

    public RecipeDescriptionEntity mapRecipeDescriptionToNewEntity(AddUserRecipeRequestDto recipeRequestDto, UserRecipeEntity userRecipeEntity) {
        return new RecipeDescriptionEntity(
                recipeRequestDto.getRecipeName(),
                recipeRequestDto.getRecipeInstructions(),
                recipeRequestDto.getIsVegetarian(),
                recipeRequestDto.getServingsNumber(),
                StringUtils.join(recipeRequestDto.getIngredients(), ","),
                userRecipeEntity
        );
    }

    public UserRecipeEntity updateRecipeEntity(ChangeRecipeRequestDto recipeRequestDto, UserRecipeEntity recipesEntity, RecipeDescriptionEntity recipeDescriptionEntity) {
        Optional<RecipeDescriptionEntity> recipeEntity = recipesEntity.getRecipeDescriptions().stream().filter(it -> it.getRecipeName().equals(recipeRequestDto.getOldRecipeName())).findFirst();

        if (recipeEntity.isPresent()) {
            RecipeDescriptionEntity entity = recipeEntity.get();
            recipesEntity.setRecipeName(recipeRequestDto.getNewRecipeName() == null ? entity.getRecipeName() : recipeRequestDto.getNewRecipeName());
            recipeDescriptionEntity.setRecipeName(recipeRequestDto.getNewRecipeName() == null ? entity.getRecipeName() : recipeRequestDto.getNewRecipeName());
            recipeDescriptionEntity.setRecipeInstructions(recipeRequestDto.getRecipeInstructions() == null ? entity.getRecipeInstructions() : recipeRequestDto.getRecipeInstructions());
            recipeDescriptionEntity.setIsVegetarian(recipeRequestDto.getIsVegetarian() == null ? entity.getIsVegetarian() : recipeRequestDto.getIsVegetarian());
            recipeDescriptionEntity.setServingsNumber(recipeRequestDto.getServingsNumber() == null ? entity.getServingsNumber() : recipeRequestDto.getServingsNumber());
            recipeDescriptionEntity.setIngredients(recipeRequestDto.getIngredients() == null ? entity.getIngredients() : StringUtils.join(recipeRequestDto.getIngredients(), ","));

            recipesEntity.getRecipeDescriptions().add(recipeDescriptionEntity);
        }
        return recipesEntity;
    }
}
