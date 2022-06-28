package example.recipes.db.repository;


import example.recipes.db.model.RecipeDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeDescriptionRepository extends JpaRepository<RecipeDescriptionEntity, UUID> {


    //RecipeDescriptionEntity findByRec(String userId, String recipeName);

}
