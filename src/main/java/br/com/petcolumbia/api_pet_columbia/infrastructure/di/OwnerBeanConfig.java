package br.com.petcolumbia.api_pet_columbia.infrastructure.di;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerValidationGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.service.OwnerValidationService;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class OwnerBeanConfig {

    @Bean
    public OwnerValidationService ownerValidationService(OwnerValidationGateway validationGateway) {
        return new OwnerValidationService(validationGateway);
    }

    @Bean
    public CreateOwnerUseCase createOwnerUseCase(OwnerGateway ownerGateway, OwnerValidationService validationService) {
        return new CreateOwnerUseCase(ownerGateway, validationService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public UpdateOwnerPasswordByIdUseCase updateOwnerPasswordByIdUseCase(OwnerGateway ownerGateway, br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerCredentialsGateway ownerCredentialsGateway) {
        return new UpdateOwnerPasswordByIdUseCase(ownerGateway, ownerCredentialsGateway);
    }

    @Bean
    public OwnerByEmailUseCase ownerByEmailUseCase(OwnerGateway ownerGateway) {
        return new OwnerByEmailUseCase(ownerGateway);
    }

    @Bean
    public UpdateOwnerUseCase updateOwnerUseCase(OwnerGateway ownerGateway) {
        return new UpdateOwnerUseCase(ownerGateway);
    }

    @Bean
    public OwnerByIdUseCase ownerByIdUseCase(OwnerGateway ownerGateway) {
        return new OwnerByIdUseCase(ownerGateway);
    }

}
