package example.recipes.models.request;


import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;


@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ChangeRecipeRequestDto {

    private String userId;
    private String recipeName;
    private String recipeInstructions;
    private Boolean isVegetarian;
    private Integer servingsNumber;
    private List<String> ingredients;
    private ZonedDateTime createdTime;

}
