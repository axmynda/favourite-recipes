package example.recipes.mappers;

import example.recipes.db.model.UserRecipesEntity;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;

import java.time.ZonedDateTime;

public class UserRecipesMapper {

    public UserRecipesEntity mapUserRecipeToNewEntity(AddUserRecipeRequestDto recipeRequestDto) {
        return new UserRecipesEntity(
                recipeRequestDto.getUserId(),
                recipeRequestDto.getRecipeName(),
                recipeRequestDto.getRecipeInstructions(),
                recipeRequestDto.getIsVegetarian(),
                recipeRequestDto.getServingsNumber(),
                ZonedDateTime.now()
        );
    }

    public void updateRecipeEntity(ChangeRecipeRequestDto recipeRequestDto, UserRecipesEntity recipesEntity) {
        recipesEntity.setRecipeName(recipeRequestDto.getRecipeName());
        recipesEntity.setRecipeInstructions(recipeRequestDto.getRecipeInstructions());
        recipesEntity.setNumberOfServings(recipeRequestDto.getServingsNumber());
        recipesEntity.setIsVegetarian(recipeRequestDto.getIsVegetarian());
    }
}
