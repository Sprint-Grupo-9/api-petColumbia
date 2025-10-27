package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LeastProceduresTimingResponse;

public class GetLeastProceduresTimingUseCase {

    private final DashboardGateway dashboardGateway;

    public GetLeastProceduresTimingUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    public LeastProceduresTimingResponse execute() {
        return dashboardGateway.getLeastProceduresTimingLastThirtyDays();
    }
}

