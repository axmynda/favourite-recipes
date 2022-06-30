package example.recipes.db.repository;


import example.recipes.db.model.RecipeDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDescriptionRepository extends JpaRepository<RecipeDescriptionEntity, Long> {

    @Modifying
    @Query(value = "delete from recipe_description u where u.user_id =?1 and u.recipe_name=?2", nativeQuery = true)
    void delete(Long userId, String recipeName);

    @Query(value = "select * from recipe_description u where u.user_id =?1 and u.recipe_name=?2", nativeQuery = true)
    RecipeDescriptionEntity find(Long userId, String recipeName);


}
