package example.recipes;

import example.recipes.db.model.RecipeDescriptionEntity;
import example.recipes.models.response.UserRecipeInfoResponseDto;

import java.util.List;

public class Stub {


    public static List<RecipeDescriptionEntity> getRecipeDescriptionEntityList() {

        return List.of(
                new RecipeDescriptionEntity(
                        "Borsch",
                        "1",
                        "Make veg Borsch",
                        true,
                        2,
                        "salt,meat,sugar"),
                new RecipeDescriptionEntity(
                        "Borsch",
                        "1",
                        "Make meat Borsch",
                        false,
                        5, "salt,meat,sugar"
                ),
                new RecipeDescriptionEntity(
                        "Borsch",
                        "1",
                        "Make salty Borsch",
                        false,
                        2, "salt,meat,sugar"),
                new RecipeDescriptionEntity(
                        "Borsch",
                        "1",
                        "Make Borsch",
                        false,
                        2, "salt,meat,sugar"),
                new RecipeDescriptionEntity(
                        "Borsch",
                        "1",
                        "Make cold Borsch",
                        true,
                        3, "salt,meat,sugar"));

    }

    public static UserRecipeInfoResponseDto getUserRecipeInfoResponseDto() {

        return new UserRecipeInfoResponseDto("1",
                "Borsch",
                getRecipeDescriptionEntityList()
        );
    }

}
