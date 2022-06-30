package example.recipes.mappers;

import example.recipes.db.model.RecipeDescriptionEntity;
import example.recipes.db.model.UserRecipeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static example.recipes.Stub.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UserRecipesMapper.class)
class UserRecipesMapperTest {

    @Autowired
    UserRecipesMapper userRecipesMapper;

    @Test
    void test_success_update_user_recipe_entity() {
        var request = getChangeUserRecipeRequestDto();
        UserRecipeEntity userRecipeEntity = getUserRecipeEntity();
        RecipeDescriptionEntity recipeDescriptionEntity = getRecipeDescriptionEntity();
        userRecipeEntity.getRecipeDescriptions().add(recipeDescriptionEntity);


        UserRecipeEntity updateEntity = userRecipesMapper.updateRecipeEntity(request, userRecipeEntity, recipeDescriptionEntity);
        RecipeDescriptionEntity actual = updateEntity.getRecipeDescriptions().get(0);

        assertEquals(request.getUserId(), updateEntity.getUserId());
        assertEquals(request.getNewRecipeName(), updateEntity.getRecipeName());
        assertEquals(request.getRecipeInstructions(), actual.getRecipeInstructions());
        assertEquals(request.getIsVegetarian(), actual.getIsVegetarian());
        assertEquals(request.getServingsNumber(), actual.getServingsNumber());

    }


    @Test
    void test_success_map_new_user_recipe_entity() {
        var request = getAddUserRecipeRequestDto();

        var actual = userRecipesMapper.mapUserRecipeToNewEntity(request);

        assertEquals(request.getUserId(), actual.getUserId());
        assertEquals(request.getRecipeName(), actual.getRecipeName());

    }

    @Test
    void test_success_map_new_recipe_description_entity() {
        var request = getAddUserRecipeRequestDto();
        var entity = getUserRecipeEntity();

        var actual = userRecipesMapper.mapRecipeDescriptionToNewEntity(request, entity);

        assertEquals(request.getRecipeName(), actual.getRecipeName());
        assertEquals(request.getIsVegetarian(), actual.getIsVegetarian());
        assertEquals(request.getServingsNumber(), actual.getServingsNumber());
        assertEquals(request.getRecipeInstructions(), actual.getRecipeInstructions());

    }


}