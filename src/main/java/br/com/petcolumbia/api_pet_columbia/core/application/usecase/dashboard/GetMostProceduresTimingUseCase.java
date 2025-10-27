package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.TopProceduresTimingResponse;

public class GetMostProceduresTimingUseCase {

    private final DashboardGateway dashboardGateway;

    public GetMostProceduresTimingUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    public TopProceduresTimingResponse execute() {
        return dashboardGateway.getMostProceduresTimingLastThirtyDays();
    }
}

