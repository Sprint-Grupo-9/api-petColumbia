package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.AppointmentBusyTimeResponse;
import br.com.petcolumbia.api_pet_columbia.repositories.IAppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private final IAppointmentRepository appointmentRepository;

    public AppointmentService(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<AppointmentBusyTimeResponse> appointmentByDateAndEmployee(
            EmployeeModel employee, LocalDate date){

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59, 999999999);

        List<AppointmentModel> busyAppointments = appointmentRepository
                .findBusyTimesByEmployeeAndDate(employee, startOfDay, endOfDay);

        return toBusyTimesDto(busyAppointments);
    }

    public List<AppointmentBusyTimeResponse> toBusyTimesDto(List<AppointmentModel> busyAppointments){
        List<AppointmentBusyTimeResponse> busyTimes = new ArrayList<>();

        for(AppointmentModel appointment: busyAppointments){

            AppointmentBusyTimeResponse busyTime = new AppointmentBusyTimeResponse();
            busyTime.setStartDateTime(appointment.getStartDateTime().toLocalTime());
            busyTime.setEndDateTime(appointment.getEndDateTime().toLocalTime());

            busyTimes.add(busyTime);
        }

        return busyTimes;
    }
}
