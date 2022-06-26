package example.recipes.models.request;


import lombok.*;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AddUserRecipeRequestDto {

   private String userId;
   private String recipeName;
   private String recipeInstructions;
   private Boolean isVegetarian;
   private Integer servingsNumber;
   private List<String> ingredients;

}
