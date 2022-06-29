package example.recipes.db.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Data
@Table(name = "recipe_description")
public class RecipeDescriptionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    String recipeName;

    @Column
    String recipeInstructions;

    @Column
    Boolean isVegetarian;

    @Column
    Integer servingsNumber;

    @Column
    String ingredients;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserRecipeEntity userRecipe;

    public RecipeDescriptionEntity() {
    }

    public RecipeDescriptionEntity(String recipeName, String recipeInstructions, Boolean isVegetarian, Integer servingsNumber, String ingredients, UserRecipeEntity userRecipe) {
        this.recipeName = recipeName;
        this.recipeInstructions = recipeInstructions;
        this.isVegetarian = isVegetarian;
        this.servingsNumber = servingsNumber;
        this.ingredients = ingredients;
        this.userRecipe = userRecipe;
    }


}



