package example.recipes.mappers;

import example.recipes.db.model.UserRecipesEntity;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
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

    //or use @Query in repository
    public void updateRecipeEntity(ChangeRecipeRequestDto recipeRequestDto, UserRecipesEntity recipesEntity) {
        recipesEntity.setRecipeName(recipeRequestDto.getRecipeName());
        recipesEntity.setRecipeInstructions(recipeRequestDto.getRecipeInstructions() == null ? recipesEntity.getRecipeInstructions() : recipeRequestDto.getRecipeInstructions());
        recipesEntity.setNumberOfServings(recipeRequestDto.getServingsNumber() == null ? recipesEntity.getNumberOfServings() : recipeRequestDto.getServingsNumber());
        recipesEntity.setIsVegetarian(recipeRequestDto.getIsVegetarian() == null ? recipesEntity.getIsVegetarian() : recipeRequestDto.getIsVegetarian());
    }
}
