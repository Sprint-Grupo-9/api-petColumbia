package br.com.petcolumbia.api_pet_columbia.infrastructure.di;

import br.com.petcolumbia.api_pet_columbia.core.adapter.employee.EmployeeGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.employee.FindEmployeeByIdUseCase;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.employee.ListEmployeesByPetOfferingsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeBeanConfig {

    @Bean
    public FindEmployeeByIdUseCase findEmployeeByIdUseCase(EmployeeGateway employeeGateway) {
        return new FindEmployeeByIdUseCase(employeeGateway);
    }

    @Bean
    public ListEmployeesByPetOfferingsUseCase listEmployeesByPetOfferingsUseCase(EmployeeGateway employeeGateway) {
        return new ListEmployeesByPetOfferingsUseCase(employeeGateway);
    }
}

