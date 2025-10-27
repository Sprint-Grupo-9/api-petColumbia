package br.com.petcolumbia.api_pet_columbia.infrastructure.di;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetBeanConfig {

    @Bean
    public CreatePetUseCase createPetUseCase(PetGateway petGateway, OwnerGateway ownerGateway) {
        return new CreatePetUseCase(petGateway, ownerGateway);
    }

    @Bean
    public FindPetByIdUseCase findPetByIdUseCase(PetGateway petGateway) {
        return new FindPetByIdUseCase(petGateway);
    }

    @Bean
    public ListAllPetsByOwnerUseCase listAllPetsByOwnerUseCase(PetGateway petGateway, OwnerGateway ownerGateway) {
        return new ListAllPetsByOwnerUseCase(petGateway, ownerGateway);
    }

    @Bean
    public DeletePetByIdUseCase deletePetByIdUseCase(PetGateway petGateway) {
        return new DeletePetByIdUseCase(petGateway);
    }

    @Bean
    public UpdatePetByIdUseCase updatePetByIdUseCase(PetGateway petGateway) {
        return new UpdatePetByIdUseCase(petGateway);
    }
}

