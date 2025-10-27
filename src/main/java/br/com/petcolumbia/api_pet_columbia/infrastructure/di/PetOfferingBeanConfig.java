package br.com.petcolumbia.api_pet_columbia.infrastructure.di;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingPriceAndDurationGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet_offering.GetPetOfferingsPricesByPetUseCase;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet_offering.ListAllPetOfferingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetOfferingBeanConfig {

    @Bean
    public ListAllPetOfferingUseCase listAllPetOfferingsUseCase(PetOfferingGateway petOfferingGateway) {
        return new ListAllPetOfferingUseCase(petOfferingGateway);
    }

    @Bean
    public GetPetOfferingsPricesByPetUseCase getPetOfferingsPricesByPetUseCase(
            PetOfferingPriceAndDurationGateway priceAndDurationGateway
    ) {
        return new GetPetOfferingsPricesByPetUseCase(priceAndDurationGateway);
    }
}

