package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.LeastServiceResponseDto;

public class GetLeastPerformedProcedureUseCase {

    private final DashboardGateway dashboardGateway;

    public GetLeastPerformedProcedureUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    public LeastServiceResponseDto execute() {
        return dashboardGateway.getLeastPerformedProcedureLastThirtyDays();
    }
}

