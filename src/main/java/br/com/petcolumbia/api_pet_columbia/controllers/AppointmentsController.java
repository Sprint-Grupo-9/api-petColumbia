package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.models.AvailableTimesModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.ServiceResponseDto;
import br.com.petcolumbia.api_pet_columbia.services.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @GetMapping("/available-times/{petId}")
    @Operation(summary = "Lista horários disponíveis de agendamento",
            description = "Receba o dia, pet e serviços solicitados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AvailableTimesModel>> getAvailableTimes(
            @RequestBody LocalDate date, @PathVariable Integer petId, @RequestBody List<ServiceResponseDto> services){
        List<AvailableTimesModel> allAvailableTimes = appointmentService.getAvailableTimes(date, petId, services);
        return ResponseEntity.status(200).body(allAvailableTimes);
    }


}
