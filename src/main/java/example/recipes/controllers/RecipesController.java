package example.recipes.controllers;

import example.recipes.db.model.UserRecipesEntity;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;
import example.recipes.services.RecipesService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.info.Info;

import java.util.List;


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

    private final RecipesService recipesService;

    @Operation(summary = "Add your own recipe")
    @PostMapping
    public ResponseEntity<String> addUserRecipe(@RequestBody AddUserRecipeRequestDto recipeRequestDto) {

        recipesService.addUserRecipe(recipeRequestDto);
   //     log.info("Prepare transaction request: {}", command);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Getting all recipes for user")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<UserRecipesEntity>> getUserFavouriteRecipes(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(recipesService.getUserRecipes(userId));
    }

    @Operation(summary = "Delete recipe by user id")
    @DeleteMapping("/{userId}/{recipeName}")
    public ResponseEntity<String> deleteUserRecipe(@PathVariable("userId") String userId,
                                                   @PathVariable("recipeName") String recipeName){
        recipesService.deleteUserRecipe(userId, recipeName);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update recipe by user id and recipe name")
    @PutMapping("/description")
    public ResponseEntity<String> updateUserRecipe(@RequestBody ChangeRecipeRequestDto recipeRequestDto){
     //   log.debug("PUT /captured-cards/status request received for deviceUid: {}", updateCapturedCardsStatusRequest.deviceUid);
        recipesService.updateUserRecipe(recipeRequestDto);
        return ResponseEntity.ok().build();
    }
}