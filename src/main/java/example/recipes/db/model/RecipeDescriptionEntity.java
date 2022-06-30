package example.recipes.db.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRecipeEntity userRecipe;

    public RecipeDescriptionEntity(String recipeName, String recipeInstructions, Boolean isVegetarian, Integer servingsNumber, String ingredients, UserRecipeEntity userRecipe) {
        this.recipeName = recipeName;
        this.recipeInstructions = recipeInstructions;
        this.isVegetarian = isVegetarian;
        this.servingsNumber = servingsNumber;
        this.ingredients = ingredients;
        this.userRecipe = userRecipe;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDescriptionEntity that = (RecipeDescriptionEntity) o;
        return Objects.equals(recipeName, that.recipeName) && Objects.equals(recipeInstructions, that.recipeInstructions) && Objects.equals(isVegetarian, that.isVegetarian) && Objects.equals(servingsNumber, that.servingsNumber) && Objects.equals(ingredients, that.ingredients) && Objects.equals(id, that.id) && Objects.equals(userRecipe, that.userRecipe);
    }

}



