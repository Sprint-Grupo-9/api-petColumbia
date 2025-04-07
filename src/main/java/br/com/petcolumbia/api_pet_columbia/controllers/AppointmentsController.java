package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.*;
import br.com.petcolumbia.api_pet_columbia.domain.models.AvailableTimesModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.AppointmentBusyTimeResponse;
import br.com.petcolumbia.api_pet_columbia.services.AppointmentService;
import br.com.petcolumbia.api_pet_columbia.services.EmployeeServiceAssociationService;
import br.com.petcolumbia.api_pet_columbia.services.PriceAndTimeService;
import br.com.petcolumbia.api_pet_columbia.services.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    private final EmployeeServiceAssociationService employeeServiceAssociation;
    private final PriceAndTimeService priceAndTimeService;
    private final AppointmentService appointmentService;
    private final ServiceService serviceService;

    public AppointmentsController(
            EmployeeServiceAssociationService employeeServiceAssociation,
            PriceAndTimeService priceAndTimeService, AppointmentService appointmentService,
            ServiceService serviceService) {
        this.employeeServiceAssociation = employeeServiceAssociation;
        this.priceAndTimeService = priceAndTimeService;
        this.appointmentService = appointmentService;
        this.serviceService = serviceService;
    }

    @GetMapping()
    public List<AvailableTimesModel> getAvailableTimes(
            @RequestParam LocalDate date, @RequestBody PetModel pet, List<ServiceModel> services){
        List<Integer> servicesIds = serviceService.getServiceIds(services);

        String servicesNames = serviceService.getServicesNames(services);

        List<EmployeeModel> employees = employeeServiceAssociation
                .listEmployeesServices(servicesIds);

        Double price = priceAndTimeService.calculateTotalPrice(servicesIds, pet);
        Integer serviceDurationMinutes = priceAndTimeService.calculateTotalDuration(servicesIds, pet);

        List<AvailableTimesModel> allAvailableTimes = new ArrayList<>();

        for(EmployeeModel employee : employees){
            AvailableTimesModel employeeAvailableTimes = getEmployeeAvailableTimes(
                    employee, date, servicesNames, price, serviceDurationMinutes);

            allAvailableTimes.add(employeeAvailableTimes);
        }

        return allAvailableTimes;
    }

    private void removeOccupiedTimes(List<LocalTime> availableTimes, AppointmentBusyTimeResponse busyTime, Integer serviceDurationMinutes) {

        //remove da lista de times os horários entre busyTime
        availableTimes.removeIf(h -> !h.isBefore(busyTime.getStartDateTime()) && h.isBefore(busyTime.getEndDateTime()));

        //remove horários que possam interferir em busyTime
        availableTimes.removeIf(h -> {
            LocalTime endTime = h.plusMinutes(serviceDurationMinutes);
            return h.isBefore(busyTime.getStartDateTime()) && !endTime.isBefore(busyTime.getStartDateTime());
        });
    }

    private AvailableTimesModel getEmployeeAvailableTimes(
            EmployeeModel employee, LocalDate date, String servicesNames, Double price, Integer serviceDurationMinutes) {

        List<LocalTime> availableTimes = new ArrayList<>(Arrays.asList(
                LocalTime.of(8, 0), LocalTime.of(8, 30), LocalTime.of(9, 0), LocalTime.of(9, 30),
                LocalTime.of(10, 0), LocalTime.of(10, 30), LocalTime.of(11, 0), LocalTime.of(11, 30),
                LocalTime.of(12, 0), LocalTime.of(12, 30), LocalTime.of(13, 0), LocalTime.of(13, 30),
                LocalTime.of(14, 0), LocalTime.of(14, 30), LocalTime.of(15, 0), LocalTime.of(15, 30),
                LocalTime.of(16, 0), LocalTime.of(16, 30), LocalTime.of(17, 0)
        ));

        List<AppointmentBusyTimeResponse> busyTimes = appointmentService.appointmentByDateAndEmployee(employee, date);

        if (busyTimes.isEmpty())
            return new AvailableTimesModel(employee, availableTimes, servicesNames, price);

        for (AppointmentBusyTimeResponse busyTime : busyTimes) {
            removeOccupiedTimes(availableTimes, busyTime, serviceDurationMinutes);
        }

        return new AvailableTimesModel(employee, availableTimes, servicesNames, price);
    }

}
