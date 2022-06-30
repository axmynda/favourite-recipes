package example.recipes.db.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_recipe")
public class UserRecipeEntity {


    @Column
    String recipeName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String userId;

    @Column
    private ZonedDateTime creationDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "userRecipe", cascade = CascadeType.ALL)
    private List<RecipeDescriptionEntity> recipeDescriptions = new ArrayList<>();

    public UserRecipeEntity(String userId, String recipeName, ZonedDateTime creationDate) {
        this.userId = userId;
        this.recipeName = recipeName;
        this.creationDate = creationDate;
    }


    public void addRecipeDescription(RecipeDescriptionEntity recipeDescription) {
        recipeDescription.setUserRecipe(this);
        recipeDescriptions.add(recipeDescription);
    }

    public void removeRecipeDescription(RecipeDescriptionEntity recipeDescription) {
        recipeDescriptions.remove(recipeDescription);
        recipeDescription.setUserRecipe(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRecipeEntity that = (UserRecipeEntity) o;
        return Objects.equals(recipeName, that.recipeName) && Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(creationDate, that.creationDate) && Objects.equals(recipeDescriptions, that.recipeDescriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeName, id, userId, creationDate, recipeDescriptions);
    }

    @Override
    public String toString() {
        return "UserRecipeEntity{" +
                "recipeName='" + recipeName + '\'' +
                ", id=" + id +
                ", userId='" + userId + '\'' +
                ", creationDate=" + creationDate +
                ", recipeDescriptions=" + recipeDescriptions +
                '}';
    }
}



