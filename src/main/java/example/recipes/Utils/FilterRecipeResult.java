package example.recipes.Utils;

import example.recipes.db.model.UserRecipeEntity;
import example.recipes.db.model.RecipeDescriptionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilterRecipeResult {

    public List<UserRecipeEntity> filterRecipes(Boolean isVegetarian, Integer servingsNumber, String specificIngredientsInclude, String specificIngredientsExclude, String textSearch, List<UserRecipeEntity> userRecipesEntities) {
        filterVegetarianRecipes(isVegetarian, userRecipesEntities);
        filterServingsNumber(servingsNumber, userRecipesEntities);
        filterSpecificIncludeIngredients(specificIngredientsInclude, userRecipesEntities);
        filterSpecificExcludeIngredients(specificIngredientsExclude, userRecipesEntities);
        filterTextSearch(textSearch, userRecipesEntities);
        return userRecipesEntities;
    }

    private List<UserRecipeEntity> filterTextSearch(String textSearch, List<UserRecipeEntity> userRecipesEntities) {

        if (textSearch != null) {
            return userRecipesEntities.stream().filter(it -> it.getRecipeDescription().stream().anyMatch(s -> s.getRecipeInstructions().contains(textSearch)))
                    .collect(Collectors.toList());
        }
        return userRecipesEntities;
    }

    private List<UserRecipeEntity> filterSpecificIncludeIngredients(String specificIngredientsInclude, List<UserRecipeEntity> userRecipesEntities) {

        if (specificIngredientsInclude != null) {
            return userRecipesEntities.stream().filter(it -> it.getRecipeDescription().stream().anyMatch(s -> s.getRecipeInstructions().equals(specificIngredientsInclude)))
                    .collect(Collectors.toList());
        }
        return userRecipesEntities;
    }

    private List<UserRecipeEntity> filterSpecificExcludeIngredients(String specificIngredientsExclude, List<UserRecipeEntity> userRecipesEntities) {

        if (specificIngredientsExclude != null) {
            return userRecipesEntities.stream().filter(it -> it.getRecipeDescription().stream().anyMatch(s -> !s.getRecipeInstructions().equals(specificIngredientsExclude)))
                    .collect(Collectors.toList());
        }
        return userRecipesEntities;
    }

    private List<UserRecipeEntity> filterServingsNumber(Integer servingsNumber, List<UserRecipeEntity> userRecipesEntities) {
        if (servingsNumber != null) {
            return userRecipesEntities.stream().filter(it -> it.getRecipeDescription().stream().anyMatch(s -> s.getServingsNumber().equals(servingsNumber)))
                    .collect(Collectors.toList());
        }
        return userRecipesEntities;

    }

    private List<UserRecipeEntity> filterVegetarianRecipes(Boolean isVegetarian, List<UserRecipeEntity> userRecipesEntities) {
        if (isVegetarian != null && isVegetarian.equals(true)) {
            return userRecipesEntities.stream().filter(it -> it.getRecipeDescription().stream().anyMatch(RecipeDescriptionEntity::getIsVegetarian))
                    .collect(Collectors.toList());
            //  return userRecipesEntities.stream().filter(UserEntity::getIsVegetarian).collect(Collectors.toList());
        }
        return userRecipesEntities;
    }

}
