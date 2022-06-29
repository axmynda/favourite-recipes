package example.recipes.db.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@Table(name = "user_recipe")
public class UserRecipeEntity {

    @Column
    String recipeName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String userId;
    @Column
    private ZonedDateTime creationDate;

    //  @OneToMany(mappedBy = "userRecipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeDescriptionEntity> recipeDescriptions = new ArrayList<>();

    public UserRecipeEntity(String userId, String recipeName, ZonedDateTime creationDate) {
        this.userId = userId;
        this.recipeName = recipeName;
        this.creationDate = creationDate;
    }


/*    public void addRecipeDescription(RecipeDescriptionEntity recipeDescription) {
        recipeDescription.setUserRecipe(this);
        recipeDescriptions.add(recipeDescription);
    }

    public void addStudent(RecipeDescriptionEntity student) {
        recipeDescriptions.add(student);
        student.setUserRecipe(this);
    }*/

/*    public void removeStudent(Student student) {
        students.remove(student);
        student.setClass(null);
    }*/

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



