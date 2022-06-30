package example.recipes.utils;

import example.recipes.db.model.UserRecipeEntity;
import example.recipes.exceptions.UserRecipeNotFoundException;
import example.recipes.models.response.UserRecipeInfoResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FilterRecipeResult {


    public UserRecipeInfoResponseDto filterRecipes(Boolean isVegetarian, Integer servingsNumber, String specificIngredientsInclude, String specificIngredientsExclude, String textSearch, List<UserRecipeEntity> userRecipeEntities) {


        List<UserRecipeEntity> filterVegetarianRecipes = filterVegetarianRecipes(isVegetarian, userRecipeEntities);
        List<UserRecipeEntity> filterServingsNumber = filterServingsNumber(servingsNumber, filterVegetarianRecipes);
        List<UserRecipeEntity> filterSpecificIncludeIngredients = filterSpecificIncludeIngredients(specificIngredientsInclude, filterServingsNumber);
        List<UserRecipeEntity> filterSpecificExcludeIngredients = filterSpecificExcludeIngredients(specificIngredientsExclude, filterSpecificIncludeIngredients);
        List<UserRecipeEntity> allFilters = filterTextSearch(textSearch, filterSpecificExcludeIngredients);

        Optional<UserRecipeEntity> entity = userRecipeEntities.stream().findFirst();
        if (entity.isPresent()) {
            return new UserRecipeInfoResponseDto(
                    entity.get().getUserId(),
                    entity.get().getRecipeName(),
                    allFilters);
        } else {
            throw new UserRecipeNotFoundException("Recipe for user was not found in database");
        }
    }

    private List<UserRecipeEntity> filterTextSearch(String textSearch, List<UserRecipeEntity> userRecipesEntities) {

        if (textSearch != null) {
            return userRecipesEntities.stream().filter(it -> it.getRecipeDescriptions().stream().allMatch(s -> s.getRecipeInstructions().contains(textSearch)))
                    .collect(Collectors.toList());
        }
        return userRecipesEntities;
    }

    private List<UserRecipeEntity> filterSpecificIncludeIngredients(String specificIngredientsInclude, List<UserRecipeEntity> userRecipesEntities) {

        if (specificIngredientsInclude != null) {
            return userRecipesEntities.stream().filter(it -> it.getRecipeDescriptions().stream().allMatch(s -> s.getIngredients().equals(specificIngredientsInclude)))
                    .collect(Collectors.toList());
        }
        return userRecipesEntities;
    }

    private List<UserRecipeEntity> filterSpecificExcludeIngredients(String specificIngredientsExclude, List<UserRecipeEntity> userRecipesEntities) {

        if (specificIngredientsExclude != null) {
            return userRecipesEntities.stream().filter(it -> it.getRecipeDescriptions().stream().noneMatch(s -> s.getIngredients().equals(specificIngredientsExclude)))
                    .collect(Collectors.toList());
        }
        return userRecipesEntities;
    }

    private List<UserRecipeEntity> filterServingsNumber(Integer servingsNumber, List<UserRecipeEntity> userRecipesEntities) {
        if (servingsNumber != null) {
            return userRecipesEntities.stream().filter(it -> it.getRecipeDescriptions().stream().allMatch(s -> s.getServingsNumber().equals(servingsNumber)))
                    .collect(Collectors.toList());
        }
        return userRecipesEntities;

    }

    private List<UserRecipeEntity> filterVegetarianRecipes(Boolean isVegetarian, List<UserRecipeEntity> userRecipesEntities) {
        if (isVegetarian != null) {
            return userRecipesEntities.stream().filter(it -> it.getRecipeDescriptions().stream().allMatch(s -> s.getIsVegetarian().equals(isVegetarian)))
                    .collect(Collectors.toList());
        }
        return userRecipesEntities;
    }

}
