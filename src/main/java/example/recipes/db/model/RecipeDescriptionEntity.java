package example.recipes.db.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Data
@Table(name = "recipe_description")
public class RecipeDescriptionEntity {

    @Column
    String recipeName;
    @Column
    String recipeInstructions;
    @Column
    Boolean isVegetarian;
    @Column
    Integer servingsNumber;
    @Column
    String ingredients;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String userId;

/*    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserRecipeEntity userRecipe;*/

    public RecipeDescriptionEntity() {
    }

    public RecipeDescriptionEntity(String recipeName, String userId, String recipeInstructions, Boolean isVegetarian, Integer servingsNumber, String ingredients) {
        this.recipeName = recipeName;
        this.userId = userId;
        this.recipeInstructions = recipeInstructions;
        this.isVegetarian = isVegetarian;
        this.servingsNumber = servingsNumber;
        this.ingredients = ingredients;
    }


}



