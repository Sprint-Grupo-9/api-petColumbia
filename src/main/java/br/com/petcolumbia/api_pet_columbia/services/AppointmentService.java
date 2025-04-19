package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.*;
import br.com.petcolumbia.api_pet_columbia.domain.models.AvailableTimesModel;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.AppointmentCreateDto;
import br.com.petcolumbia.api_pet_columbia.repositories.IPetRepository;
import br.com.petcolumbia.api_pet_columbia.repositories.EmployeeRepository;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.AppointmentResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.BusyTimeResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.PetResponseDto;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityConflictException;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.repositories.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


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
    private final IPetRepository petRepository;
    private final EmployeeRepository employeeRepository;





    public AppointmentService(IAppointmentRepository appointmentRepository, ServiceService serviceService, PriceAndTimeService priceAndTimeService, EmployeeService employeeService, PetService petService,  IPetRepository petRepository, EmployeeRepository employeeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.serviceService = serviceService;
        this.priceAndTimeService = priceAndTimeService;
        this.employeeServiceAssociation = employeeService;
        this.petService = petService;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;

    }

    public List<AvailableTimesModel> getAvailableTimes(LocalDate date, Integer petId, List<ServiceModel> services){

        PetResponseDto pet  = petService.findPetById(petId);

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

    private void removeOccupiedTimes(List<LocalTime> availableTimes, BusyTimeResponseDto busyTime, Integer serviceDurationMinutes) {

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

        List<BusyTimeResponseDto> busyTimes = appointmentByDateAndEmployee(employee, date);

        if (busyTimes.isEmpty())
            return new AvailableTimesModel(employee, availableTimes, servicesNames, price);

        for (BusyTimeResponseDto busyTime : busyTimes) {
            removeOccupiedTimes(availableTimes, busyTime, serviceDurationMinutes);
        }

        return new AvailableTimesModel(employee, availableTimes, servicesNames, price);
    }

    public List<BusyTimeResponseDto> toBusyTimesDto(List<AppointmentModel> busyAppointments){
        List<BusyTimeResponseDto> busyTimes = new ArrayList<>();

        for(AppointmentModel appointment: busyAppointments){

            BusyTimeResponseDto busyTime = new BusyTimeResponseDto();
            busyTime.setStartDateTime(appointment.getStartDateTime().toLocalTime());
            busyTime.setEndDateTime(appointment.getEndDateTime().toLocalTime());

            busyTimes.add(busyTime);
        }

        return busyTimes;
    }

    public List<BusyTimeResponseDto> appointmentByDateAndEmployee(
            EmployeeModel employee, LocalDate date){

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59, 999999999);

        List<AppointmentModel> busyAppointments = appointmentRepository
                .findByEmployeeAndStartDateTimeGreaterThanEqualAndStartDateTimeLessThan(employee, startOfDay, endOfDay);

        return toBusyTimesDto(busyAppointments);
    }

    public AppointmentModel createAppointment(AppointmentCreateDto dto) {
        AppointmentModel appointment = createDtoToEntity(dto);
        return appointmentRepository.save(appointment);
    }

    private AppointmentModel createDtoToEntity(AppointmentCreateDto dto) {
        PetModel pet = petRepository.findById(dto.getPet())
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

        EmployeeModel employee = employeeRepository.findById(dto.getEmployee_id())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        AppointmentModel appointment = new AppointmentModel();
        appointment.setPet(pet);
        appointment.setEmployee(employee);
        appointment.setServices(dto.getServices().toString()); // Pode melhorar usando JSON se quiser
        appointment.setTotalPrice(dto.getTotalPrice());
        appointment.setStartDateTime(dto.getStartDateTime());
        appointment.setEndDateTime(dto.getStartDateTime().plusMinutes(dto.getDurationMinutes()));
        appointment.setFinished(false);
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setLastUpdate(LocalDateTime.now());

        return appointment;
    }


    public AppointmentResponseDto updateAppointmentById(Integer id, AppointmentCreateDto dto) {

        AppointmentModel appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));

        PetModel pet = petRepository.findById(dto.getPet())
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

        EmployeeModel employee = employeeRepository.findById(dto.getEmployee_id())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        appointment.setPet(pet);
        appointment.setEmployee(employee);
        appointment.setServices(dto.getServices().toString());
        appointment.setTotalPrice(dto.getTotalPrice());
        appointment.setStartDateTime(dto.getStartDateTime());
        appointment.setEndDateTime(dto.getStartDateTime().plusMinutes(dto.getDurationMinutes()));
        appointment.setLastUpdate(LocalDateTime.now());


        appointmentRepository.save(appointment);

        return new AppointmentResponseDto(appointment);
    }

    public void deleteAppointmentById(Integer id) {
        if(!appointmentRepository.existsById(id))
            throw new EntityNotFoundException("Não encontrado usuário com id:" + id);

        appointmentRepository.deleteById(id);
    }
}
