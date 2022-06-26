package example.recipes.services;

import example.recipes.db.model.UserRecipesEntity;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;

import java.util.List;

public interface RecipesService {

    void addUserRecipe(AddUserRecipeRequestDto recipeRequestDto);

    List<UserRecipesEntity> getUserRecipes(String userId);

    void deleteUserRecipe(String userId, String userRecipe);

    void updateUserRecipe(ChangeRecipeRequestDto recipeRequestDto);
}
