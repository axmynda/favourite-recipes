package example.recipes.Utils;

import example.recipes.db.model.RecipeDescriptionEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static example.recipes.Stub.getRecipeDescriptionEntityList;
import static example.recipes.Stub.getUserRecipeInfoResponseDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.of;

@SpringBootTest(classes = FilterRecipeResult.class)
class FilterRecipeResultTest {

    @Autowired
    FilterRecipeResult filterRecipeResult;


    private static Stream<Arguments> filterCombinations() {
        List<RecipeDescriptionEntity> entityList = getRecipeDescriptionEntityList();
        return Stream.of(
                of(false, null, null, null, "Make Borsch", entityList),
                of(true, 1, "nuts", "meat", "search text", entityList),
                of(true, 1, "nuts", "meat", "search text", entityList),
                of(true, 1, "nuts", "meat", "search text", entityList),
                of(true, 1, "nuts", "meat", "search text", entityList)
        );
    }

    @ParameterizedTest
    @MethodSource("filterCombinations")
    void filterRecipesSuccess(Boolean isVegetarian, Integer servingsNumber, String specificIngredientsInclude,
                              String specificIngredientsExclude, String textSearch, List<RecipeDescriptionEntity> recipeDescriptionEntities) {


        var expected = getUserRecipeInfoResponseDto();
        var actual = assertDoesNotThrow(() -> filterRecipeResult.filterRecipes(isVegetarian, servingsNumber, specificIngredientsInclude, specificIngredientsExclude, textSearch, recipeDescriptionEntities));

        assertEquals(expected.getRecipeName(), actual.getRecipeName());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertTrue(expected.getRecipeDescriptionEntities().containsAll(actual.getRecipeDescriptionEntities()));

       /* Pair<String, String> splitted = assertDoesNotThrow(() -> macMessageSplitter.splitMessageByCommandAndMacSign(message));
        assertEquals(splitted.getRight(), macSign);
        assertTrue(message.contains(splitted.getLeft()));
        assertTrue(message.contains(splitted.getRight()));*/
    }
}