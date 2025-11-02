package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;

import java.time.LocalDate;
import java.util.Map;

public class GetAmountPetOfferingsLastSevenDaysUseCase {

    private final DashboardGateway dashboardGateway;

    public GetAmountPetOfferingsLastSevenDaysUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    public Map<LocalDate, Long> execute() {
        return dashboardGateway.getAmountPetOfferingsCountPerDay();
    }
}

