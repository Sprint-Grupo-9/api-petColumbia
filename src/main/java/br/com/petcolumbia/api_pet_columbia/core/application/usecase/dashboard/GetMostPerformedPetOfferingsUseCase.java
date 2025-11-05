package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.TopPetOfferingResponseDto;

public class GetMostPerformedPetOfferingsUseCase {

    private final DashboardGateway dashboardGateway;

    public GetMostPerformedPetOfferingsUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    public TopPetOfferingResponseDto execute() {
        return dashboardGateway.getMostPerformedPetOfferingLastThirtyDays();
    }
}

