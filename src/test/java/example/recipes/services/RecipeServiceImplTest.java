package example.recipes.services;

import example.recipes.Utils.FilterRecipeResult;
import example.recipes.db.repository.UserRecipeRepository;
import example.recipes.mappers.UserRecipesMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static example.recipes.Stub.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = RecipeServiceImpl.class)
class RecipeServiceImplTest {

    @Autowired
    RecipeServiceImpl recipeService;

    @MockBean
    UserRecipeRepository userRecipeRepository;

    @MockBean
    UserRecipesMapper userRecipesMapper;

    @MockBean
    FilterRecipeResult filter;


    @Test
    void test_success_add_user_recipe() {
        var addUserRecipeRequest = getAddUserRecipeRequestDto();

        doReturn(getUserRecipeEntity())
                .when(userRecipesMapper)
                .mapUserRecipeToNewEntity(addUserRecipeRequest);

        doReturn(getRecipeDescriptionEntity())
                .when(userRecipesMapper)
                .mapRecipeDescriptionToNewEntity(addUserRecipeRequest, getUserRecipeEntity());

        getUserRecipeEntity().getRecipeDescriptions().add(getRecipeDescriptionEntity());
        recipeService.addUserRecipe(addUserRecipeRequest);


        verify(userRecipesMapper).mapUserRecipeToNewEntity(addUserRecipeRequest);
        //   verify(userRecipesMapper).mapRecipeDescriptionToNewEntity(addUserRecipeRequest, getUserRecipeEntity());
        //   verify(userRecipeRepository).save(getUserRecipeEntity());

    }

    @Test
    void test_success_delete_user_recipe() {
        recipeService.deleteUserRecipe(TEST_USER_ID, "pizza");
        verify(userRecipeRepository).delete(any(), any());
    }

    @Test
    void test_success_get_user_recipes() {
        doReturn(getUserRecipeEntities())
                .when(userRecipeRepository)
                .findAllByUserId(TEST_USER_ID);

        recipeService.getUserRecipes(TEST_USER_ID, false, 2, null, null, null);

        verify(userRecipeRepository).findAllByUserId(TEST_USER_ID);
        verify(filter).filterRecipes(false, 2, null, null, null, getUserRecipeEntities());

    }

    @Test
    void updateUserRecipe() {
    }
}