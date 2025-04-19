package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.*;
import br.com.petcolumbia.api_pet_columbia.domain.models.AvailableTimesModel;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.AppointmentCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.AppointmentResponseDto;
import br.com.petcolumbia.api_pet_columbia.services.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
    private final AppointmentService appointmentService;

    public AppointmentsController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Lista horários disponíveis de agendamento",
            description = "Receba o dia, pet e serviços solicitados")
    @GetMapping()
    public ResponseEntity<List<AvailableTimesModel>> getAvailableTimes(
            @RequestBody LocalDate date, @PathVariable Integer petId, @RequestBody List<ServiceModel> services){
        List<AvailableTimesModel> allAvailableTimes = appointmentService.getAvailableTimes(date, petId, services);
        return ResponseEntity.status(200).body(allAvailableTimes);
    }

    @Operation(summary = "Regista um novo agendamento, Recebe um dto de criação de usuário")
    @PostMapping
    public ResponseEntity<AppointmentModel>registerAppointment(@RequestBody AppointmentCreateDto newAppointment){
        AppointmentModel savedAppointment = appointmentService.createAppointment(newAppointment);
        return ResponseEntity.status(201).body(savedAppointment);
    }

    @Operation(summary = "Atualiza um agendamento pelo id e objeto", description = "recebe o id e objeto para atualizar, com exceção da senha")
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable Integer id, @RequestBody AppointmentCreateDto dto) {
        AppointmentResponseDto updatedAppointment = appointmentService.updateAppointmentById(id, dto);
        return ResponseEntity.status(200).body(updatedAppointment);
    }

    @Operation(summary = "Deleta um agendamento pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.status(204).build();
    }
}
