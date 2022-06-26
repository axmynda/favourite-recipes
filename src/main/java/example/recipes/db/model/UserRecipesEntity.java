package example.recipes.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_recipes")
public class UserRecipesEntity {

    @Id
    @Column
    private String userId;

    @Column(unique = true, nullable = false)
    String recipeName;

    @Column
    String recipeInstructions;

    @Column
    Boolean isVegetarian;

    @Column
    Integer numberOfServings;

/*    @Column
    List<String> ingredients;*/

    @Column
    private ZonedDateTime creationDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserRecipesEntity that = (UserRecipesEntity) o;

        return new EqualsBuilder().append(userId, that.userId).append(recipeName, that.recipeName).append(recipeInstructions, that.recipeInstructions).append(isVegetarian, that.isVegetarian).append(numberOfServings, that.numberOfServings).append(creationDate, that.creationDate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(userId).append(recipeName).append(recipeInstructions).append(isVegetarian).append(numberOfServings).append(creationDate).toHashCode();
    }

    @Override
    public String toString() {
        return "UserRecipesEntity{" +
                "userId='" + userId + '\'' +
                ", recipeName='" + recipeName + '\'' +
                ", recipeInstructions='" + recipeInstructions + '\'' +
                ", isVegetarian=" + isVegetarian +
                ", numberOfServings=" + numberOfServings +
                ", creationDate=" + creationDate +
                '}';
    }
}



