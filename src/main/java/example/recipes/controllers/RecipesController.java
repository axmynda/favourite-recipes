package example.recipes.controllers;

import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;
import example.recipes.models.response.UserRecipeInfoResponseDto;
import example.recipes.services.RecipesService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@AllArgsConstructor
@OpenAPIDefinition(
        info = @Info(title = "Recipes api",
                version = "0.1",
                description = "The application allows adding, updating, removing and fetching recipes."
        )
)
@RequestMapping("/v1/api/recipe")
public class RecipesController {
    //TODO add http code adapter
    private final RecipesService recipesService;

    @Operation(summary = "Add your own recipe")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addUserRecipe(@RequestBody AddUserRecipeRequestDto recipeRequestDto) {
        recipesService.addUserRecipe(recipeRequestDto);
    }

    @Operation(summary = "Getting all recipes for user")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserRecipeInfoResponseDto> getUserFavouriteRecipes(@PathVariable("userId") String userId,
                                                                             @RequestParam(required = false) Boolean isVegetarian,
                                                                             @RequestParam(required = false) Integer servingsNumber,
                                                                             @RequestParam(required = false) String specificIngredientsInclude,
                                                                             @RequestParam(required = false) String specificIngredientsExclude,
                                                                             @RequestParam(required = false) String textSearch) {
        return ResponseEntity.ok(recipesService.getUserRecipes(userId, isVegetarian, servingsNumber, specificIngredientsInclude, specificIngredientsExclude, textSearch));
    }


    @Operation(summary = "Delete recipe by user id")
    @DeleteMapping("/{userId}/{recipeName}")
    public ResponseEntity<String> deleteUserRecipe(@PathVariable("userId") String userId,
                                                   @PathVariable("recipeName") String recipeName) {
        recipesService.deleteUserRecipe(userId, recipeName);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update recipe by user id and recipe name")
    @PatchMapping("/edit")
    public ResponseEntity<String> editUserRecipe(@RequestBody ChangeRecipeRequestDto recipeRequestDto) {
        recipesService.updateUserRecipe(recipeRequestDto);
        return ResponseEntity.ok().build();
    }
}
