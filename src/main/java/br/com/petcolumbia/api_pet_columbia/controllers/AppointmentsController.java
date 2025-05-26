package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.domain.models.AvailableTimesModel;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.AppointmentMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.ServiceMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.appointmentDtos.AppointmentUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.others.AvailableTimesRequest;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.appointmentDtos.AppointmentCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos.AppointmentResponseDto;
import br.com.petcolumbia.api_pet_columbia.services.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
    private final AppointmentService appointmentService;

    public AppointmentsController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/available-times/{petId}")
    @Operation(summary = "Lista horários disponíveis de agendamento",
            description = "Receba o dia, pet e serviços solicitados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AvailableTimesModel>> getAvailableTimes(@RequestBody AvailableTimesRequest availableTimesRequest, @PathVariable Integer petId){
        List<AvailableTimesModel> allAvailableTimes = appointmentService.getAvailableTimes(availableTimesRequest.getDate(), petId, ServiceMapper.requestsToEntities(availableTimesRequest.getServices()));
        return ResponseEntity.status(200).body(allAvailableTimes);
    }
    
    @GetMapping("/{ownerId}")
    @Operation(summary = "Lista todos os agendamentos de um usuário pelo id")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsByOwnerId(@PathVariable Integer ownerId){
        List<AppointmentModel> all = appointmentService.allAppointmentsByOwnerId(ownerId);
        return ResponseEntity.status(201).body(AppointmentMapper.entitiesToResponses(all));
    }

    @PostMapping()
    @Operation(summary = "Regista um novo agendamento, Recebe um dto de criação de agendamento")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AppointmentResponseDto> registerAppointment(@RequestBody AppointmentCreateDto newAppointment){
        AppointmentModel savedAppointment = appointmentService.createAppointment(newAppointment);
        return ResponseEntity.status(201).body(AppointmentMapper.entityToResponse(savedAppointment));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um agendamento pelo id e objeto", description = "recebe o id e objeto para atualizar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable Integer id, @RequestBody AppointmentUpdateDto dto) {
        AppointmentModel updatedAppointment = appointmentService.updateAppointmentById(id, dto);
        return ResponseEntity.status(200).body(AppointmentMapper.entityToResponse(updatedAppointment));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um agendamento pelo id")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.status(204).build();
    }
}
