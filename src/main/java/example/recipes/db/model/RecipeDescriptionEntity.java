package example.recipes.db.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Data
@Table(name = "recipe_description")
public class RecipeDescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    String recipeName;

    @Column
    String recipeInstructions;

    @Column
    Boolean isVegetarian;

    @Column
    Integer servingsNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserRecipeEntity userRecipe;

    public RecipeDescriptionEntity() {
    }

    public RecipeDescriptionEntity(String recipeName, String recipeInstructions, Boolean isVegetarian, Integer servingsNumber, UserRecipeEntity userRecipe) {
        this.recipeName = recipeName;
        this.recipeInstructions = recipeInstructions;
        this.isVegetarian = isVegetarian;
        this.servingsNumber = servingsNumber;
        this.userRecipe = userRecipe;
    }



}



