package example.recipes.services;

import example.recipes.db.model.UserRecipesEntity;
import example.recipes.db.repository.UserRecipesRepository;
import example.recipes.exceptions.UserRecipeNotFoundException;
import example.recipes.mappers.UserRecipesMapper;
import example.recipes.models.request.AddUserRecipeRequestDto;
import example.recipes.models.request.ChangeRecipeRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipesService {

    private final UserRecipesRepository userRecipesRepository;
    private final UserRecipesMapper userRecipesMapper;

    @Override
    public void addUserRecipe(AddUserRecipeRequestDto recipeRequestDto) {
        UserRecipesEntity userRecipesEntity = userRecipesMapper.mapUserRecipeToNewEntity(recipeRequestDto);
        userRecipesRepository.save(userRecipesEntity);
    }

    @Override
    public List<UserRecipesEntity> getUserRecipes(String userId) {
        return userRecipesRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteUserRecipe(String userId, String userRecipe) {

        userRecipesRepository.deleteByUserIdAndRecipeName(userId, userRecipe); //TODO обработать EmptyResultDataAccessException

    }

    @Override
    @Transactional
    public void updateUserRecipe(ChangeRecipeRequestDto recipeRequestDto) {

        String userId = recipeRequestDto.getUserId();
        String recipeName = recipeRequestDto.getRecipeName();
        UserRecipesEntity recipesEntity = userRecipesRepository.findByUserIdAndRecipeName(userId, recipeName).
                orElseThrow(() -> new UserRecipeNotFoundException(String.format("recipe %s for user %s was not found in database", recipeName, userId)));

        //  UserRecipesEntity recipesEntity = recipesEntityOptional.get();
        userRecipesMapper.updateRecipeEntity(recipeRequestDto, recipesEntity);
        userRecipesRepository.save(recipesEntity);
    }

}