package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.TopServiceResponseDto;

public class GetMostPerformedProcedureUseCase {

    private final DashboardGateway dashboardGateway;

    public GetMostPerformedProcedureUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    public TopServiceResponseDto execute() {
        return dashboardGateway.getMostPerformedProcedureLastThirtyDays();
    }
}

