package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.implementations;

import br.com.petcolumbia.api_pet_columbia.core.adapter.appointment.AppointmentGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.BusyTimeResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.common.PageResponse;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.AppointmentEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.AppointmentEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.EmployeeEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.AppointmentJpaRepository;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.EmployeeJpaRepository;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.PetJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentJpaAdapter implements AppointmentGateway {

    private final AppointmentJpaRepository appointmentRepository;
    private final PetJpaRepository petRepository;
    private final EmployeeJpaRepository employeeRepository;

    public AppointmentJpaAdapter(
            AppointmentJpaRepository appointmentRepository,
            PetJpaRepository petRepository,
            EmployeeJpaRepository employeeRepository
    ) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Appointment> findAllAppointmentsByOwnerId(Integer ownerId) {
        List<AppointmentEntity> entities = appointmentRepository.findAllAppointmentsByOwnerId(ownerId);

        // Use AppointmentFullMapping para carregar dados completos
        MappingContext context = new MappingContext(new MappingStrategy.AppointmentFullMapping());
        return AppointmentEntityMapper.toDomainList(entities, context);
    }

    @Override
    public PageResponse<Appointment> findAllAppointmentsByOwnerIdPaginated(Integer ownerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AppointmentEntity> pageResult = appointmentRepository.findAllAppointmentsByOwnerIdPaginated(ownerId, pageable);

        // Use AppointmentFullMapping para carregar dados completos
        MappingContext context = new MappingContext(new MappingStrategy.AppointmentFullMapping());
        List<Appointment> appointments = AppointmentEntityMapper.toDomainList(pageResult.getContent(), context);

        return new PageResponse<>(
                appointments,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.isFirst(),
                pageResult.isLast(),
                pageResult.isEmpty()
        );
    }

    @Override
    public Appointment createAppointment(AppointmentCreateCommand command) {
        PetEntity pet = petRepository.findById(command.petId())
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

        EmployeeEntity employee = employeeRepository.findById(command.employeeId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        AppointmentEntity entity = new AppointmentEntity();
        entity.setPet(pet);
        entity.setEmployee(employee);
        entity.setPetOfferings(command.petOfferingNames());
        entity.setObservations(command.observations());

        // Tratar taxiService null como false
        Boolean taxiService = command.taxiService() != null ? command.taxiService() : false;
        entity.setTaxiService(taxiService);

        if (Boolean.TRUE.equals(taxiService)) {
            entity.setTotalPrice(command.totalPrice() + 20.0);
        } else {
            entity.setTotalPrice(command.totalPrice());
        }

        entity.setStartDateTime(command.startDateTime());
        entity.setEndDateTime(command.startDateTime().plusMinutes(command.durationMinutes()));
        entity.setFinished(false);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setLastUpdate(LocalDateTime.now());

        AppointmentEntity savedEntity = appointmentRepository.saveAndFlush(entity);

        // Map com contexto completo
        MappingContext context = new MappingContext(new MappingStrategy.AppointmentFullMapping());
        return AppointmentEntityMapper.toDomain(savedEntity, context);
    }

    @Override
    public Appointment updateAppointmentById(Integer id, AppointmentUpdateCommand command) {
        AppointmentEntity entity = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));

        PetEntity pet = petRepository.findById(command.petId())
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

        EmployeeEntity employee = employeeRepository.findById(command.employeeId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        entity.setPet(pet);
        entity.setEmployee(employee);
        entity.setPetOfferings(command.petOfferingNames());
        entity.setObservations(command.observations());

        // Tratar taxiService null como false
        Boolean taxiService = command.taxiService() != null ? command.taxiService() : false;
        entity.setTaxiService(taxiService);

        if (Boolean.TRUE.equals(taxiService)) {
            entity.setTotalPrice(command.totalPrice() + 20.0);
        } else {
            entity.setTotalPrice(command.totalPrice());
        }

        entity.setStartDateTime(command.startDateTime());
        entity.setEndDateTime(command.startDateTime().plusMinutes(command.durationMinutes()));
        entity.setLastUpdate(LocalDateTime.now());

        AppointmentEntity savedEntity = appointmentRepository.saveAndFlush(entity);

        // Map com contexto completo
        MappingContext context = new MappingContext(new MappingStrategy.AppointmentFullMapping());
        return AppointmentEntityMapper.toDomain(savedEntity, context);
    }

    @Override
    public void deleteAppointmentById(Integer id) {
        if (!appointmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Agendamento não encontrado com id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<BusyTimeResponseDto> findBusyTimesByEmployeeAndDate(Integer employeeId, LocalDate date) {
        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59, 999999999);

        List<AppointmentEntity> busyAppointments = appointmentRepository
                .findByEmployeeAndStartDateTimeGreaterThanEqualAndStartDateTimeLessThan(employee, startOfDay, endOfDay);

        return toBusyTimesDto(busyAppointments);
    }

    @Override
    public List<Appointment> findAppointmentsByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59, 999999999);

        List<AppointmentEntity> entities = appointmentRepository.findByStartDateTimeBetween(startOfDay, endOfDay);

        // Use AppointmentFullMapping para carregar dados completos
        MappingContext context = new MappingContext(new MappingStrategy.AppointmentFullMapping());
        return AppointmentEntityMapper.toDomainList(entities, context);
    }

    @Override
    public boolean existsById(Integer id) {
        return appointmentRepository.existsById(id);
    }

    private List<BusyTimeResponseDto> toBusyTimesDto(List<AppointmentEntity> busyAppointments) {
        List<BusyTimeResponseDto> busyTimes = new ArrayList<>();

        for (AppointmentEntity appointment : busyAppointments) {
            BusyTimeResponseDto busyTime = new BusyTimeResponseDto(
                    appointment.getStartDateTime().toLocalTime(),
                    appointment.getEndDateTime().toLocalTime()
            );
            busyTimes.add(busyTime);
        }

        return busyTimes;
    }
}

