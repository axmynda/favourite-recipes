package example.recipes.models.response;


import example.recipes.db.model.UserRecipeEntity;
import lombok.*;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UserRecipeInfoResponseDto {

    private String userId;
    private String recipeName;
    private List<UserRecipeEntity> recipeDescriptionEntities;

}
