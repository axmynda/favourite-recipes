package example.recipes.utils;

import example.recipes.db.model.UserRecipeEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static example.recipes.Stub.getUserRecipeEntity;
import static example.recipes.Stub.getUserRecipeInfoResponseDto;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

@SpringBootTest(classes = FilterRecipeResult.class)
class FilterRecipeResultTest {

    @Autowired
    FilterRecipeResult filterRecipeResult;


    private static Stream<Arguments> filterCombinations() {
        List<UserRecipeEntity> entityList = List.of(getUserRecipeEntity());
        return Stream.of(
                of(true, null, null, null, "Make Borsch", entityList),
                of(true, 1, "nuts", "meat", "search text", entityList),
                of(true, 1, "nuts", "meat", "search text", entityList),
                of(true, 1, "nuts", "meat", "search text", entityList),
                of(true, 1, "nuts", "meat", "search text", entityList)
        );
    }

    @ParameterizedTest
    @MethodSource("filterCombinations")
    void filterRecipesSuccess(Boolean isVegetarian, Integer servingsNumber, String specificIngredientsInclude,
                              String specificIngredientsExclude, String textSearch, List<UserRecipeEntity> recipeDescriptionEntities) {


        var expected = getUserRecipeInfoResponseDto();
        var actual = assertDoesNotThrow(() -> filterRecipeResult.filterRecipes(isVegetarian, servingsNumber, specificIngredientsInclude, specificIngredientsExclude, textSearch, recipeDescriptionEntities));

        assertEquals(expected.getRecipeName(), actual.getRecipeName());
        assertEquals(expected.getUserId(), actual.getUserId());
        //       assertTrue(expected.getRecipeDescriptionEntities().containsAll(actual.getRecipeDescriptionEntities()));

    }
}
