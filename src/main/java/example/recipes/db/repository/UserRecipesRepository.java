package example.recipes.db.repository;


import example.recipes.db.model.UserRecipesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRecipesRepository extends JpaRepository<UserRecipesEntity, String> {

    List<UserRecipesEntity> findAllByUserId(String userId);

    Optional<UserRecipesEntity> findByUserIdAndRecipeName(String userId, String recipeName);

    @Modifying
    @Query("delete from UserRecipesEntity u where u.userId=:userId and u.recipeName=:recipeName")
    void deleteByUserIdAndRecipeName(@Param("userId") String userId, @Param("recipeName") String recipeName);

}
