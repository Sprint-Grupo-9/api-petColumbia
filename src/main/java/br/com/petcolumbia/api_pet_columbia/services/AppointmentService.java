package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.domain.models.AvailableTimesModel;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.AppointmentMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.EmployeeMapper;
import br.com.petcolumbia.api_pet_columbia.domain.entities.*;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.AppointmentCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.AppointmentUpdateDto;
import br.com.petcolumbia.api_pet_columbia.repositories.IPetRepository;
import br.com.petcolumbia.api_pet_columbia.repositories.EmployeeRepository;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.AppointmentResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.BusyTime;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.repositories.IAppointmentRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AppointmentService {

    private final IAppointmentRepository appointmentRepository;
    private final ServiceService serviceService;
    private final PriceAndTimeService priceAndTimeService;
    private final EmployeeService employeeServiceAssociation;
    private final PetService petService;
    private final EmployeeService employeeService;

    public AppointmentService(IAppointmentRepository appointmentRepository, ServiceService serviceService, PriceAndTimeService priceAndTimeService, EmployeeService employeeServiceAssociation, PetService petService, EmployeeService employeeService) {
        this.appointmentRepository = appointmentRepository;
        this.serviceService = serviceService;
        this.priceAndTimeService = priceAndTimeService;
        this.employeeServiceAssociation = employeeServiceAssociation;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    public List<AvailableTimesModel> getAvailableTimes(LocalDate date, Integer petId, List<ServiceModel> services){

        PetModel pet = petService.findPetById(petId);

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

    private void removeOccupiedTimes(List<LocalTime> availableTimes, BusyTime busyTime, Integer serviceDurationMinutes) {

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

        List<BusyTime> busyTimes = appointmentByDateAndEmployee(employee, date);

        if (busyTimes.isEmpty())
            return new AvailableTimesModel(EmployeeMapper.entityToResponse(employee), availableTimes, serviceDurationMinutes, servicesNames, price);

        for (BusyTime busyTime : busyTimes) {
            removeOccupiedTimes(availableTimes, busyTime, serviceDurationMinutes);
        }

        return new AvailableTimesModel(EmployeeMapper.entityToResponse(employee), availableTimes, serviceDurationMinutes, servicesNames, price);
    }

    public List<BusyTime> toBusyTimesDto(List<AppointmentModel> busyAppointments){
        List<BusyTime> busyTimes = new ArrayList<>();

        for(AppointmentModel appointment: busyAppointments){

            BusyTime busyTime = new BusyTime();
            busyTime.setStartDateTime(appointment.getStartDateTime().toLocalTime());
            busyTime.setEndDateTime(appointment.getEndDateTime().toLocalTime());

            busyTimes.add(busyTime);
        }

        return busyTimes;
    }

    public List<BusyTime> appointmentByDateAndEmployee(
            EmployeeModel employee, LocalDate date){

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59, 999999999);

        List<AppointmentModel> busyAppointments = appointmentRepository
                .findByEmployeeAndStartDateTimeGreaterThanEqualAndStartDateTimeLessThan(employee, startOfDay, endOfDay);

        return toBusyTimesDto(busyAppointments);
    }

    public List<AppointmentModel> appointmentsByDate(LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59);

        return appointmentRepository.findByStartDateTimeBetween(startOfDay, endOfDay);
    }

    public AppointmentModel createAppointment(AppointmentCreateDto dto) {
        PetModel pet = petService.findPetById(dto.getPetId());

        EmployeeModel employee = employeeService.findEmployeeById(dto.getEmployee_id());

        AppointmentModel appointment = new AppointmentModel();

        appointment.setPet(pet);
        appointment.setEmployee(employee);
        appointment.setServices(dto.getServices().toString());
        appointment.setTotalPrice(dto.getTotalPrice());
        appointment.setStartDateTime(dto.getStartDateTime());
        appointment.setEndDateTime(dto.getStartDateTime().plusMinutes(dto.getDurationMinutes()));
        appointment.setFinished(false);
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setLastUpdate(LocalDateTime.now());

        appointmentRepository.saveAndFlush(appointment);

        return appointment;
    }

    public AppointmentModel updateAppointmentById(Integer id, AppointmentUpdateDto dto) {
        AppointmentModel appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));

        PetModel pet = petService.findPetById(dto.getPetId());

        EmployeeModel employee = employeeService.findEmployeeById(dto.getEmployee_id());

        appointment.setPet(pet);
        appointment.setEmployee(employee);
        appointment.setServices(dto.getServices().toString());
        appointment.setTotalPrice(dto.getTotalPrice());
        appointment.setStartDateTime(dto.getStartDateTime());
        appointment.setEndDateTime(dto.getStartDateTime().plusMinutes(dto.getDurationMinutes()));
        appointment.setLastUpdate(LocalDateTime.now());

        appointmentRepository.saveAndFlush(appointment);

        return appointment;
    }

    public void deleteAppointmentById(Integer id) {
        if(!appointmentRepository.existsById(id))
            throw new EntityNotFoundException("Não encontrado usuário com id:" + id);

        appointmentRepository.deleteById(id);
    }
}
