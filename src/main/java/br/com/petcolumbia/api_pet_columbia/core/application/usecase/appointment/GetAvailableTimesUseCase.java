package br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.employee.EmployeeGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingPriceAndDurationGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AvailableTimesResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.BusyTimeResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.employee.EmployeeResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper.EmployeeResponseMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetAvailableTimesUseCase {

    private final AppointmentGateway appointmentGateway;
    private final PetOfferingGateway petOfferingGateway;
    private final PetOfferingPriceAndDurationGateway priceAndDurationGateway;
    private final EmployeeGateway employeeGateway;
    private final PetGateway petGateway;

    public GetAvailableTimesUseCase(
            AppointmentGateway appointmentGateway,
            PetOfferingGateway petOfferingGateway,
            PetOfferingPriceAndDurationGateway priceAndDurationGateway,
            EmployeeGateway employeeGateway,
            PetGateway petGateway
    ) {
        this.appointmentGateway = appointmentGateway;
        this.petOfferingGateway = petOfferingGateway;
        this.priceAndDurationGateway = priceAndDurationGateway;
        this.employeeGateway = employeeGateway;
        this.petGateway = petGateway;
    }

    public List<AvailableTimesResponseDto> execute(LocalDate date, Integer petId, List<Integer> petOfferingIds) {
        Pet pet = petGateway.findPetById(petId, null); // null para owner pois não precisa validar

        if (pet == null) {
            throw new EntityNotFoundException("Pet não encontrado");
        }

        String petOfferingNames = petOfferingGateway.getPetOfferingsNamesByIds(petOfferingIds);

        List<Employee> employees = employeeGateway.listEmployeesByPetOfferings(petOfferingIds);

        Double price = priceAndDurationGateway.calculateTotalPrice(petOfferingIds, pet.getSize(), pet.getCoat());

        Integer serviceDurationMinutes = priceAndDurationGateway.calculateTotalDuration(petOfferingIds, pet.getSize(), pet.getCoat());

        List<AvailableTimesResponseDto> allAvailableTimes = new ArrayList<>();

        for (Employee employee : employees) {
            AvailableTimesResponseDto employeeAvailableTimes = getEmployeeAvailableTimes(
                    employee, date, petOfferingNames, price, serviceDurationMinutes);

            allAvailableTimes.add(employeeAvailableTimes);
        }

        return allAvailableTimes;
    }

    private void removeOccupiedTimes(List<LocalTime> availableTimes, BusyTimeResponseDto busyTime, Integer serviceDurationMinutes) {
        // Remove da lista de times os horários entre busyTime
        availableTimes.removeIf(h -> !h.isBefore(busyTime.getStartDateTime()) && h.isBefore(busyTime.getEndDateTime()));

        // Remove horários que possam interferir em busyTime
        availableTimes.removeIf(h -> {
            LocalTime endTime = h.plusMinutes(serviceDurationMinutes);
            return h.isBefore(busyTime.getStartDateTime()) && !endTime.isBefore(busyTime.getStartDateTime());
        });
    }

    private AvailableTimesResponseDto getEmployeeAvailableTimes(
            Employee employee, LocalDate date, String petOfferingNames, Double price, Integer serviceDurationMinutes) {

        List<LocalTime> availableTimes = new ArrayList<>(Arrays.asList(
                LocalTime.of(9, 0), LocalTime.of(9, 30),
                LocalTime.of(10, 0), LocalTime.of(10, 30), LocalTime.of(11, 0), LocalTime.of(11, 30),
                LocalTime.of(12, 0), LocalTime.of(12, 30), LocalTime.of(13, 0), LocalTime.of(13, 30),
                LocalTime.of(14, 0), LocalTime.of(14, 30), LocalTime.of(15, 0), LocalTime.of(15, 30),
                LocalTime.of(16, 0), LocalTime.of(16, 30)
        ));

        List<BusyTimeResponseDto> busyTimes = appointmentGateway.findBusyTimesByEmployeeAndDate(employee.getId(), date);

        EmployeeResponseDto employeeDto = EmployeeResponseMapper.toResponse(employee);

        if (busyTimes.isEmpty()) {
            return new AvailableTimesResponseDto(employeeDto, availableTimes, serviceDurationMinutes, petOfferingNames, price);
        }

        for (BusyTimeResponseDto busyTime : busyTimes) {
            removeOccupiedTimes(availableTimes, busyTime, serviceDurationMinutes);
        }

        return new AvailableTimesResponseDto(employeeDto, availableTimes, serviceDurationMinutes, petOfferingNames, price);
    }
}

