package example.recipes.db.repository;


import example.recipes.db.model.RecipeDescriptionEntity;
import example.recipes.db.model.UserRecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecipeDescriptionRepository extends JpaRepository<RecipeDescriptionEntity, UUID> {

    //List<UserEntity> findAllByUserId(String userId);

  //  Optional<UserRecipeEntity> findByUserIdAndRecipeName(String userId, String recipeName);



/*    @Modifying
    @Query("UPDATE UserRecipesEntity ur SET (ur.recipeName = :recipeName) and (:recipeInstructions is null "
            + " or ur.recipeInstructions = :recipeInstructions) and (:isVegetarian is null or ur.isVegetarian = :isVegetarian) " +
            "and (:numberOfServings is null or ur.numberOfServings = :numberOfServings) WHERE ur.recipeName = :recipeName ")

    void updateRecipe(@Param("recipeName") String recipeName, @Param("recipeInstructions") String recipeInstructions,
                      @Param("isVegetarian") Boolean isVegetarian, @Param("numberOfServings") Integer numberOfServings);*/


}
