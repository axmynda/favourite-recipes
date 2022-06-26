package example.recipes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableJpaRepositories(basePackageClasses = DeviceCertificateRepository.class)
@EnableTransactionManagement
public class AppConfig {
}