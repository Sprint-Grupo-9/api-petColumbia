package br.com.petcolumbia.api_pet_columbia.infrastructure.web;

import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.appointment.AppointmentUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AppointmentResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AvailableTimesResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.appointment.*;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.command_mapper.AppointmentCommandMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper.AppointmentResponseMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.appointment.AppointmentCreateDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.appointment.AppointmentUpdateDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.appointment.AvailableTimesRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final GetAvailableTimesUseCase getAvailableTimesUseCase;
    private final ListAllAppointmentsByOwnerUseCase listAllAppointmentsByOwnerUseCase;
    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final UpdateAppointmentByIdUseCase updateAppointmentByIdUseCase;
    private final DeleteAppointmentByIdUseCase deleteAppointmentByIdUseCase;

    public AppointmentController(
            GetAvailableTimesUseCase getAvailableTimesUseCase,
            ListAllAppointmentsByOwnerUseCase listAllAppointmentsByOwnerUseCase,
            CreateAppointmentUseCase createAppointmentUseCase,
            UpdateAppointmentByIdUseCase updateAppointmentByIdUseCase,
            DeleteAppointmentByIdUseCase deleteAppointmentByIdUseCase
    ) {
        this.getAvailableTimesUseCase = getAvailableTimesUseCase;
        this.listAllAppointmentsByOwnerUseCase = listAllAppointmentsByOwnerUseCase;
        this.createAppointmentUseCase = createAppointmentUseCase;
        this.updateAppointmentByIdUseCase = updateAppointmentByIdUseCase;
        this.deleteAppointmentByIdUseCase = deleteAppointmentByIdUseCase;
    }

    @PostMapping("/available-times/{petId}")
    @Operation(summary = "Lista horários disponíveis de agendamento",
            description = "Recebe o dia, pet e procedimentos solicitados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AvailableTimesResponseDto>> getAvailableTimes(
            @RequestBody AvailableTimesRequestDto request,
            @PathVariable Integer petId
    ) {
        List<AvailableTimesResponseDto> availableTimes = getAvailableTimesUseCase.execute(
                request.getDate(),
                petId,
                request.getProcedureIds()
        );
        return ResponseEntity.ok(availableTimes);
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Lista todos os agendamentos de um usuário pelo id")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsByOwnerId(
            @PathVariable Integer ownerId
    ) {
        List<Appointment> appointments = listAllAppointmentsByOwnerUseCase.execute(ownerId);

        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AppointmentResponseDto> response = AppointmentResponseMapper.toResponseList(appointments);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Registra um novo agendamento", description = "Recebe um dto de criação de agendamento")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AppointmentResponseDto> createAppointment(
            @Valid @RequestBody AppointmentCreateDto dto
    ) {
        AppointmentCreateCommand command = AppointmentCommandMapper.toCommand(dto);
        Appointment appointment = createAppointmentUseCase.execute(command);
        AppointmentResponseDto response = AppointmentResponseMapper.toResponse(appointment);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um agendamento pelo id e objeto", description = "Recebe o id e objeto para atualizar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(
            @PathVariable Integer id,
            @Valid @RequestBody AppointmentUpdateDto dto
    ) {
        AppointmentUpdateCommand command = AppointmentCommandMapper.toCommand(dto);
        Appointment appointment = updateAppointmentByIdUseCase.execute(id, command);
        AppointmentResponseDto response = AppointmentResponseMapper.toResponse(appointment);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um agendamento pelo id")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id) {
        deleteAppointmentByIdUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}

