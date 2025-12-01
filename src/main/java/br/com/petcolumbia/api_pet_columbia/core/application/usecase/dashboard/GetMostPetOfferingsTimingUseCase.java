package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.TopPetOfferingsTimingResponse;
import org.springframework.cache.annotation.Cacheable;

public class GetMostPetOfferingsTimingUseCase {

    private final DashboardGateway dashboardGateway;

    public GetMostPetOfferingsTimingUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    @Cacheable(cacheNames = "mostPetOfferingsTiming", key = "'default'")
    public TopPetOfferingsTimingResponse execute() {
        return dashboardGateway.getMostPetOfferingsTimingLastThirtyDays();
    }
}
