package br.com.petcolumbia.api_pet_columbia.infrastructure.di;

import br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner.CreateOwnerUseCase;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.jpa.jpa_adapter.OwnerJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OwnerBeanConfig {

    @Bean
    public CreateOwnerUseCase createOwnerUseCase(OwnerJpaAdapter adapter) {
        return new CreateOwnerUseCase(adapter);
    }
}
