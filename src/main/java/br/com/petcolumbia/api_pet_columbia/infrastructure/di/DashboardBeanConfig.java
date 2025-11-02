package br.com.petcolumbia.api_pet_columbia.infrastructure.di;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DashboardBeanConfig {

    @Bean
    public GetAppointmentsByDateUseCase getAppointmentsByDateUseCase(DashboardGateway dashboardGateway) {
        return new GetAppointmentsByDateUseCase(dashboardGateway);
    }

    @Bean
    public GetAmountPetOfferingsLastSevenDaysUseCase getAmountPetOfferingsLastSevenDaysUseCase(
            DashboardGateway dashboardGateway
    ) {
        return new GetAmountPetOfferingsLastSevenDaysUseCase(dashboardGateway);
    }

    @Bean
    public GetMostPerformedPetOfferingsUseCase getMostPerformedPetOfferingsUseCase(DashboardGateway dashboardGateway) {
        return new GetMostPerformedPetOfferingsUseCase(dashboardGateway);
    }

    @Bean
    public GetLeastPerformedPetOfferingsUseCase getLeastPerformedPetOfferingsUseCase(DashboardGateway dashboardGateway) {
        return new GetLeastPerformedPetOfferingsUseCase(dashboardGateway);
    }

    @Bean
    public GetMostPetOfferingsTimingUseCase getMostPetOfferingsTimingUseCase(DashboardGateway dashboardGateway) {
        return new GetMostPetOfferingsTimingUseCase(dashboardGateway);
    }

    @Bean
    public GetLeastPetOfferingsTimingUseCase getLeastPetOfferingsTimingUseCase(DashboardGateway dashboardGateway) {
        return new GetLeastPetOfferingsTimingUseCase(dashboardGateway);
    }
}

