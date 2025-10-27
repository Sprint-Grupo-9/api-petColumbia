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
    public GetAmountProceduresLastSevenDaysUseCase getAmountProceduresLastSevenDaysUseCase(
            DashboardGateway dashboardGateway
    ) {
        return new GetAmountProceduresLastSevenDaysUseCase(dashboardGateway);
    }

    @Bean
    public GetMostPerformedProcedureUseCase getMostPerformedProcedureUseCase(DashboardGateway dashboardGateway) {
        return new GetMostPerformedProcedureUseCase(dashboardGateway);
    }

    @Bean
    public GetLeastPerformedProcedureUseCase getLeastPerformedProcedureUseCase(DashboardGateway dashboardGateway) {
        return new GetLeastPerformedProcedureUseCase(dashboardGateway);
    }

    @Bean
    public GetMostProceduresTimingUseCase getMostProceduresTimingUseCase(DashboardGateway dashboardGateway) {
        return new GetMostProceduresTimingUseCase(dashboardGateway);
    }

    @Bean
    public GetLeastProceduresTimingUseCase getLeastProceduresTimingUseCase(DashboardGateway dashboardGateway) {
        return new GetLeastProceduresTimingUseCase(dashboardGateway);
    }
}

