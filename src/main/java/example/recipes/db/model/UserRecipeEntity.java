package example.recipes.db.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;


@Entity
@Data
@Table(name = "user_recipe")
public class UserRecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String userId;

    @Column
    String recipeName;

    @OneToMany(mappedBy = "userRecipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<RecipeDescriptionEntity> recipeDescriptions;

    @Column
    private ZonedDateTime creationDate;

    public UserRecipeEntity(String userId, String recipeName, ZonedDateTime creationDate) {
        this.userId = userId;
        this.recipeName = recipeName;
        this.creationDate = creationDate;
    }

    public void addRecipeDescription(RecipeDescriptionEntity recipeDescription) {
        recipeDescription.setUserRecipe(this);
        this.recipeDescriptions.add(recipeDescription);
    }

    public UserRecipeEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserRecipeEntity that = (UserRecipeEntity) o;

        return new EqualsBuilder().append(id, that.id).append(userId, that.userId).append(recipeName, that.recipeName).append(recipeDescriptions, that.recipeDescriptions).append(creationDate, that.creationDate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(userId).append(recipeName).append(recipeDescriptions).append(creationDate).toHashCode();
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", recipeName='" + recipeName + '\'' +
                ", userRecipes=" + recipeDescriptions +
                ", creationDate=" + creationDate +
                '}';
    }
}



