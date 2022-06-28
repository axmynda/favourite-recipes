package example.recipes.config;

import example.recipes.db.repository.RecipeDescriptionRepository;
import example.recipes.db.repository.UserRecipeRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackageClasses = {UserRecipeRepository.class, RecipeDescriptionRepository.class})
@EnableTransactionManagement
public class AppConfig {
}