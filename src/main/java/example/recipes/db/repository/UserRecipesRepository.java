package example.recipes.db.repository;


import example.recipes.db.model.UserRecipesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRecipesRepository extends JpaRepository<UserRecipesEntity, String> {

    List<UserRecipesEntity> findAllByUserId(String userId);

    Optional<UserRecipesEntity> findByUserIdAndRecipeName(String userId, String recipeName);

    void deleteByUserIdAndRecipeName(String userId, String recipeName);

}
