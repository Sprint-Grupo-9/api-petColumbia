package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.*;
import br.com.petcolumbia.api_pet_columbia.domain.models.AvailableTimesModel;
import br.com.petcolumbia.api_pet_columbia.services.AppointmentService;
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

    @GetMapping()
    public ResponseEntity<List<AvailableTimesModel>> getAvailableTimes(
            @RequestBody LocalDate date, @RequestBody PetModel pet, @RequestBody List<ServiceModel> services){
        List<AvailableTimesModel> allAvailableTimes = appointmentService.getAvailableTimes(date, pet, services);
        return ResponseEntity.status(200).body(allAvailableTimes);
    }
}
