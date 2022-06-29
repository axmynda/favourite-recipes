package example.recipes.services;

import example.recipes.Utils.FilterRecipeResult;
import example.recipes.db.repository.UserRecipeRepository;
import example.recipes.mappers.UserRecipesMapper;
import example.recipes.models.request.AddUserRecipeRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static example.recipes.Stub.*;
import static example.recipes.Stub.getUserRecipeEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        verify(userRecipesMapper).mapRecipeDescriptionToNewEntity(addUserRecipeRequest, getUserRecipeEntity());
     //   verify(userRecipeRepository).save(getUserRecipeEntity());

    }


    @Test
    void getUserRecipes() {
    }

    @Test
    void deleteUserRecipe() {
    }

    @Test
    void updateUserRecipe() {
    }
}