package example.recipes.services;

import example.recipes.db.model.UserRecipeEntity;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;

import java.util.List;

public interface RecipesService {

    void addUserRecipe(AddUserRecipeRequestDto recipeRequestDto);

    List<UserRecipeEntity> getUserRecipes(String userId, Boolean isVegeterian, Integer servingsNumber, String specificIngredientsInclude, String specificIngredientsExclude, String textSearch);

    void deleteUserRecipe(String userId, String userRecipe);

    void updateUserRecipe(ChangeRecipeRequestDto recipeRequestDto);
}
