package example.recipes.db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.awt.print.Book;
import java.time.ZonedDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_recipes")
public class UserRecipesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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


/*    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredients> ingredients;*/
    @Column
    private ZonedDateTime creationDate;

    public UserRecipesEntity(String userId, String recipeName, String recipeInstructions, Boolean isVegetarian, Integer servingsNumber, ZonedDateTime creationDate) {
        this.userId = userId;
        this.recipeName = recipeName;
        this.recipeInstructions = recipeInstructions;
        this.isVegetarian = isVegetarian;
        this.numberOfServings = servingsNumber;
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserRecipesEntity that = (UserRecipesEntity) o;

        return new EqualsBuilder().append(id, that.id).append(userId, that.userId).append(recipeName, that.recipeName).append(recipeInstructions, that.recipeInstructions).append(isVegetarian, that.isVegetarian).append(numberOfServings, that.numberOfServings).append(creationDate, that.creationDate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(userId).append(recipeName).append(recipeInstructions).append(isVegetarian).append(numberOfServings).append(creationDate).toHashCode();
    }

    @Override
    public String toString() {
        return "UserRecipesEntity{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", recipeName='" + recipeName + '\'' +
                ", recipeInstructions='" + recipeInstructions + '\'' +
                ", isVegetarian=" + isVegetarian +
                ", numberOfServings=" + numberOfServings +
                ", creationDate=" + creationDate +
                '}';
    }
}



