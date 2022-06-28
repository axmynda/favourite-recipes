package example.recipes.db.repository;


import example.recipes.db.model.UserRecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRecipeRepository extends JpaRepository<UserRecipeEntity, UUID> {

    List<UserRecipeEntity> findAllByUserId(String userId);

    Optional<UserRecipeEntity> findByUserIdAndRecipeName(String userId, String recipeName);

    @Modifying
    @Query("delete from UserRecipeEntity u where u.userId=:userId and u.recipeName=:recipeName")
    void deleteByUserIdAndRecipeName(@Param("userId") String userId, @Param("recipeName") String recipeName);

}
