package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LeastPetOfferingResponseDto;

public class GetLeastPerformedPetOfferingsUseCase {

    private final DashboardGateway dashboardGateway;

    public GetLeastPerformedPetOfferingsUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    public LeastPetOfferingResponseDto execute() {
        return dashboardGateway.getLeastPerformedPetOfferingLastThirtyDays();
    }
}

