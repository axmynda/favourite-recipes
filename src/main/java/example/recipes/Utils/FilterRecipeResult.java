package example.recipes.Utils;

import example.recipes.db.model.RecipeDescriptionEntity;
import example.recipes.exceptions.UserRecipeNotFoundException;
import example.recipes.models.response.UserRecipeInfoResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FilterRecipeResult {


    public UserRecipeInfoResponseDto filterRecipes(Boolean isVegetarian, Integer servingsNumber, String specificIngredientsInclude, String specificIngredientsExclude, String textSearch, List<RecipeDescriptionEntity> recipeDescriptionEntities) {


        List<RecipeDescriptionEntity> filterVegetarianRecipes = filterVegetarianRecipes(isVegetarian, recipeDescriptionEntities);
        List<RecipeDescriptionEntity> filterServingsNumber = filterServingsNumber(servingsNumber, filterVegetarianRecipes);
        List<RecipeDescriptionEntity> filterSpecificIncludeIngredients = filterSpecificIncludeIngredients(specificIngredientsInclude, filterServingsNumber);
        List<RecipeDescriptionEntity> filterSpecificExcludeIngredients = filterSpecificExcludeIngredients(specificIngredientsExclude, filterSpecificIncludeIngredients);
        List<RecipeDescriptionEntity> allFilters = filterTextSearch(textSearch, filterSpecificExcludeIngredients);

        Optional<RecipeDescriptionEntity> entity = recipeDescriptionEntities.stream().findFirst();
        if (entity.isPresent()) {
            return new UserRecipeInfoResponseDto(
                    entity.get().getUserId(),
                    entity.get().getRecipeName(),
                    allFilters);
        } else {
            throw new UserRecipeNotFoundException(String.format("recipe for user was not found in database"/*, recipeName, userId*/));
        }
    }

    private List<RecipeDescriptionEntity> filterTextSearch(String textSearch, List<RecipeDescriptionEntity> userRecipesEntities) {

        if (textSearch != null) {
            return userRecipesEntities.stream().filter(s -> s.getRecipeInstructions().contains(textSearch))
                    .collect(Collectors.toList());
        }
        return userRecipesEntities;
    }

    private List<RecipeDescriptionEntity> filterSpecificIncludeIngredients(String specificIngredientsInclude, List<RecipeDescriptionEntity> recipeDescriptionEntities) {

        if (specificIngredientsInclude != null) {
            return recipeDescriptionEntities.stream().filter(s -> s.getIngredients().equals(specificIngredientsInclude))
                    .collect(Collectors.toList());
        }
        return recipeDescriptionEntities;
    }

    private List<RecipeDescriptionEntity> filterSpecificExcludeIngredients(String specificIngredientsExclude, List<RecipeDescriptionEntity> recipeDescriptionEntities) {

        if (specificIngredientsExclude != null) {
            return recipeDescriptionEntities.stream().filter(s -> !s.getIngredients().equals(specificIngredientsExclude))
                    .collect(Collectors.toList());
        }
        return recipeDescriptionEntities;
    }

    private List<RecipeDescriptionEntity> filterServingsNumber(Integer servingsNumber, List<RecipeDescriptionEntity> recipeDescriptionEntities) {
        if (servingsNumber != null) {
            return recipeDescriptionEntities.stream().filter(s -> s.getServingsNumber().equals(servingsNumber))
                    .collect(Collectors.toList());
        }
        return recipeDescriptionEntities;

    }

    private List<RecipeDescriptionEntity> filterVegetarianRecipes(Boolean isVegetarian, List<RecipeDescriptionEntity> recipeDescriptionEntities) {
        if (isVegetarian != null) {
            return recipeDescriptionEntities.stream().filter(it -> it.getIsVegetarian().equals(isVegetarian))
                    .collect(Collectors.toList());
        }
        return recipeDescriptionEntities;
    }

}
