package example.recipes.mappers;

import example.recipes.db.model.UserRecipeEntity;
import example.recipes.db.model.RecipeDescriptionEntity;
import example.recipes.db.repository.RecipeDescriptionRepository;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRecipesMapper {

    private final RecipeDescriptionRepository recipeDescriptionRepository;

    public UserRecipeEntity mapUserRecipeToNewEntity(AddUserRecipeRequestDto recipeRequestDto) {

        return new UserRecipeEntity(
                recipeRequestDto.getUserId(),
                recipeRequestDto.getRecipeName(),
                ZonedDateTime.now()
        );
    }

    public RecipeDescriptionEntity mapRecipeDescriptionToNewEntity(AddUserRecipeRequestDto recipeRequestDto, UserRecipeEntity userRecipeEntity){
        return  new RecipeDescriptionEntity(
                recipeRequestDto.getRecipeName(),
                recipeRequestDto.getRecipeInstructions(),
                recipeRequestDto.getIsVegetarian(),
                recipeRequestDto.getServingsNumber(),
                userRecipeEntity
        );
    }

    public UserRecipeEntity updateRecipeEntity(ChangeRecipeRequestDto recipeRequestDto, UserRecipeEntity recipesEntity) {
        Optional<RecipeDescriptionEntity> recipeEntity = recipesEntity.getRecipeDescription().stream().filter(it -> it.getRecipeName().equals(recipeRequestDto.getOldRecipeName())).findFirst();

        if(recipeEntity.isPresent()){
            RecipeDescriptionEntity entity = recipeEntity.get();
            recipesEntity.setRecipeDescription(Collections.singletonList(new RecipeDescriptionEntity(
                    (recipeRequestDto.getNewRecipeName() == null ? entity.getRecipeName() : recipeRequestDto.getNewRecipeName()),
                    recipeRequestDto.getRecipeInstructions() == null ? entity.getRecipeInstructions() : recipeRequestDto.getRecipeInstructions(),
                    recipeRequestDto.getIsVegetarian() == null ? entity.getIsVegetarian() : recipeRequestDto.getIsVegetarian(),
                    recipeRequestDto.getServingsNumber() == null ? entity.getServingsNumber() : recipeRequestDto.getServingsNumber(),
                    recipesEntity
                    )
            ));
        }
        return recipesEntity;
    }
}
