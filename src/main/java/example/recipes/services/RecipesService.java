package example.recipes.services;

import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;
import example.recipes.models.response.UserRecipeInfoResponseDto;

public interface RecipesService {

    void addUserRecipe(AddUserRecipeRequestDto recipeRequestDto);

    UserRecipeInfoResponseDto getUserRecipes(String userId, Boolean isVegeterian, Integer servingsNumber, String specificIngredientsInclude, String specificIngredientsExclude, String textSearch);

    void deleteUserRecipe(String userId, String recipeName);

    void updateUserRecipe(ChangeRecipeRequestDto recipeRequestDto);
}
