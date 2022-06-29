package example.recipes;

import example.recipes.db.model.RecipeDescriptionEntity;
import example.recipes.db.model.UserRecipeEntity;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.response.UserRecipeInfoResponseDto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class Stub {


    public static UserRecipeEntity getUserRecipeEntity() {
        return new UserRecipeEntity(
                "1",
                "Borsch",
                ZonedDateTime.ofInstant(Instant.parse("2018-10-20T16:55:30.00Z"), ZoneId.of("Europe/Paris")));
    }

    public static RecipeDescriptionEntity getRecipeDescriptionEntity() {
        return new RecipeDescriptionEntity(
                        "Borsch",
                        "Make veg Borsch",
                        true,
                        2,
                        "salt,meat,sugar",
                        getUserRecipeEntity()
        );
    }

    public static List<RecipeDescriptionEntity> getRecipeDescriptionEntityList() {
        return List.of(
                new RecipeDescriptionEntity(
                        "Borsch",
                        "Make veg Borsch",
                        true,
                        2,
                        "salt,meat,sugar",
                        getUserRecipeEntity()
                ),
                new RecipeDescriptionEntity(
                        "Borsch",
                        "Make meat Borsch",
                        false,
                        5, "salt,meat,sugar",
                        getUserRecipeEntity()
                ),
                new RecipeDescriptionEntity(
                        "Borsch",
                        "Make salty Borsch",
                        false,
                        2, "salt,meat,sugar",
                        getUserRecipeEntity()),
                new RecipeDescriptionEntity(
                        "Borsch",
                        "Make Borsch",
                        false,
                        2, "salt,meat,sugar",
                        getUserRecipeEntity()),
                new RecipeDescriptionEntity(
                        "Borsch",
                        "Make cold Borsch",
                        true,
                        3, "salt,meat,sugar",
                        getUserRecipeEntity()));

    }

    public static UserRecipeInfoResponseDto getUserRecipeInfoResponseDto() {
        return new UserRecipeInfoResponseDto("1",
                "Borsch",
                List.of(getUserRecipeEntity()
                ));
    }

    public static AddUserRecipeRequestDto getAddUserRecipeRequestDto() {
        return new AddUserRecipeRequestDto(
                "1",
                "Best pizza",
                "make pizza",
                false,
                3,
                List.of("potato", "meat", "salad")
        );
    }

}
