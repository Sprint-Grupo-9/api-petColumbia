package br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;

import java.time.LocalDate;
import java.util.Map;

public class GetAmountProceduresLastSevenDaysUseCase {

    private final DashboardGateway dashboardGateway;

    public GetAmountProceduresLastSevenDaysUseCase(DashboardGateway dashboardGateway) {
        this.dashboardGateway = dashboardGateway;
    }

    public Map<LocalDate, Long> execute() {
        return dashboardGateway.getAmountProceduresCountPerDay();
    }
}

