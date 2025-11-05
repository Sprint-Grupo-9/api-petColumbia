package br.com.petcolumbia.api_pet_columbia.infrastructure.di;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.employee.EmployeeGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingPriceAndDurationGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppointmentBeanConfig {

    @Bean
    public GetAvailableTimesUseCase getAvailableTimesUseCase(
            AppointmentGateway appointmentGateway,
            PetOfferingGateway petOfferingGateway,
            PetOfferingPriceAndDurationGateway priceAndDurationGateway,
            EmployeeGateway employeeGateway,
            PetGateway petGateway
    ) {
        return new GetAvailableTimesUseCase(
                appointmentGateway,
                petOfferingGateway,
                priceAndDurationGateway,
                employeeGateway,
                petGateway
        );
    }

    @Bean
    public ListAllAppointmentsByOwnerUseCase listAllAppointmentsByOwnerUseCase(
            AppointmentGateway appointmentGateway
    ) {
        return new ListAllAppointmentsByOwnerUseCase(appointmentGateway);
    }

    @Bean
    public CreateAppointmentUseCase createAppointmentUseCase(
            AppointmentGateway appointmentGateway
    ) {
        return new CreateAppointmentUseCase(appointmentGateway);
    }

    @Bean
    public UpdateAppointmentByIdUseCase updateAppointmentByIdUseCase(
            AppointmentGateway appointmentGateway
    ) {
        return new UpdateAppointmentByIdUseCase(appointmentGateway);
    }

    @Bean
    public DeleteAppointmentByIdUseCase deleteAppointmentByIdUseCase(
            AppointmentGateway appointmentGateway
    ) {
        return new DeleteAppointmentByIdUseCase(appointmentGateway);
    }

    @Bean
    public ListAppointmentsByDateUseCase listAppointmentsByDateUseCase(
            AppointmentGateway appointmentGateway
    ) {
        return new ListAppointmentsByDateUseCase(appointmentGateway);
    }
}

