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

    public static final String TEST_USER_ID = "1";
    public static final String TEST_RECIPE_NAME = "Borsch";


    public static UserRecipeEntity getUserRecipeEntity() {
        return new UserRecipeEntity(
                TEST_USER_ID,
                TEST_RECIPE_NAME,
                ZonedDateTime.ofInstant(Instant.parse("2018-10-20T16:55:30.00Z"), ZoneId.of("Europe/Paris")));
    }

    public static RecipeDescriptionEntity getRecipeDescriptionEntity() {
        return new RecipeDescriptionEntity(
                TEST_RECIPE_NAME,
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
                        TEST_RECIPE_NAME,
                        "Make veg Borsch",
                        true,
                        2,
                        "salt,meat,sugar",
                        getUserRecipeEntity()
                ),
                new RecipeDescriptionEntity(
                        TEST_RECIPE_NAME,
                        "Make meat Borsch",
                        false,
                        5, "salt,meat,sugar",
                        getUserRecipeEntity()
                ),
                new RecipeDescriptionEntity(
                        TEST_RECIPE_NAME,
                        "Make salty Borsch",
                        false,
                        2, "salt,meat,sugar",
                        getUserRecipeEntity()),
                new RecipeDescriptionEntity(
                        TEST_RECIPE_NAME,
                        "Make Borsch",
                        false,
                        2, "salt,meat,sugar",
                        getUserRecipeEntity()),
                new RecipeDescriptionEntity(
                        TEST_RECIPE_NAME,
                        "Make cold Borsch",
                        true,
                        3, "salt,meat,sugar",
                        getUserRecipeEntity()));

    }

    public static UserRecipeInfoResponseDto getUserRecipeInfoResponseDto() {
        return new UserRecipeInfoResponseDto(TEST_USER_ID,
                TEST_RECIPE_NAME,
                List.of(getUserRecipeEntity()
                ));
    }

    public static AddUserRecipeRequestDto getAddUserRecipeRequestDto() {
        return new AddUserRecipeRequestDto(
                TEST_USER_ID,
                "Best pizza",
                "make pizza",
                false,
                3,
                List.of("potato", "meat", "salad")
        );
    }

    public static List<UserRecipeEntity> getUserRecipeEntities() {
        return List.of(new UserRecipeEntity(
                TEST_USER_ID,
                TEST_RECIPE_NAME,
                ZonedDateTime.ofInstant(Instant.parse("2018-10-20T16:55:30.00Z"), ZoneId.of("Europe/Paris"))));
    }

}
