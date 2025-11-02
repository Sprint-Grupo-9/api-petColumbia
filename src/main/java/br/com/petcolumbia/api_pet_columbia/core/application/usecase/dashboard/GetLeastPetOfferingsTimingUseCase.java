package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LeastPetOfferingsTimingResponse;

public class GetLeastPetOfferingsTimingUseCase {

    private final DashboardGateway dashboardGateway;

    public GetLeastPetOfferingsTimingUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    public LeastPetOfferingsTimingResponse execute() {
        return dashboardGateway.getLeastPetOfferingsTimingLastThirtyDays();
    }
}

