package example.recipes.models.request;


import lombok.*;

import java.util.List;


@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ChangeRecipeRequestDto {

    private String userId;
    private String oldRecipeName;
    private String newRecipeName;
    private String recipeInstructions;
    private Boolean isVegetarian;
    private Integer servingsNumber;
    private List<String> ingredients;

}
