package example.recipes.services;

import example.recipes.Utils.FilterRecipeResult;
import example.recipes.db.model.RecipeDescriptionEntity;
import example.recipes.db.model.UserRecipeEntity;
import example.recipes.db.repository.RecipeDescriptionRepository;
import example.recipes.db.repository.UserRecipeRepository;
import example.recipes.exceptions.UserRecipeNotFoundException;
import example.recipes.mappers.UserRecipesMapper;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;
import example.recipes.models.response.UserRecipeInfoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipesService {

    private final UserRecipeRepository userRecipeRepository;
    private final RecipeDescriptionRepository recipeDescriptionRepository;
    private final UserRecipesMapper userRecipesMapper;
    private final FilterRecipeResult filter;

    @Override
    @Transactional
    public void addUserRecipe(AddUserRecipeRequestDto recipeRequestDto) {
        UserRecipeEntity userRecipeEntity = userRecipesMapper.mapUserRecipeToNewEntity(recipeRequestDto);
        RecipeDescriptionEntity recipeDescriptionEntity = userRecipesMapper.mapRecipeDescriptionToNewEntity(recipeRequestDto);
        userRecipeEntity.getRecipeDescriptions().add(recipeDescriptionEntity);
        log.info("Save userRecipeEntity in db: {}", userRecipeEntity);
        userRecipeRepository.save(userRecipeEntity);
    }

    @Override
    @Transactional
    public UserRecipeInfoResponseDto getUserRecipes(String userId, Boolean isVegetarian, Integer servingsNumber, String specificIngredientsInclude, String specificIngredientsExclude, String textSearch) {
        List<RecipeDescriptionEntity> recipeDescriptionEntities = recipeDescriptionRepository.findAllByUserId(userId);

        return filter.filterRecipes(isVegetarian, servingsNumber, specificIngredientsInclude, specificIngredientsExclude,
                textSearch, recipeDescriptionEntities);
    }

    @Override
    @Transactional
    public void deleteUserRecipe(String userId, String userRecipe) {
        userRecipeRepository.deleteByUserIdAndRecipeName(userId, userRecipe);
    }

    @Override
    @Transactional
    public void updateUserRecipe(ChangeRecipeRequestDto recipeRequestDto) {

        String userId = recipeRequestDto.getUserId();
        String recipeName = recipeRequestDto.getOldRecipeName();
        UserRecipeEntity recipesEntity = userRecipeRepository.findByUserIdAndRecipeName(userId, recipeName).
                orElseThrow(() -> new UserRecipeNotFoundException(String.format("recipe %s for user %s was not found in database", recipeName, userId)));

        UserRecipeEntity userRecipeEntity = userRecipesMapper.updateRecipeEntity(recipeRequestDto, recipesEntity);
        userRecipeRepository.save(userRecipeEntity);
    }

}