package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.AppointmentMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.dashboard.*;
import br.com.petcolumbia.api_pet_columbia.services.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboards")
public class DashboardsController {

    private final DashboardService dashboardService;

    public DashboardsController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/appointments/date")
    @Operation(summary = "Lista todos os agendamentos marcados de uma data, recebe uma data", description = "Retorna ")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AppointmentsDashboardInfosResponseDto> listAppointmentsByDate(@RequestParam LocalDate date){
        List<AppointmentModel> appointments = dashboardService.appointmentsByDate(date);

        if (appointments.isEmpty())
            return ResponseEntity.status(204).build();

        AppointmentsDashboardInfosResponseDto response  = new AppointmentsDashboardInfosResponseDto();

        LastPetAndOwnerAppointmentsResponseDto lastAppointmentsDto = dashboardService.lastAppointmentsOfPetAndOwnerByAppointmentsList(appointments);

        response.setCardResponses(AppointmentMapper.entitiesToCardInfoResponses(appointments));
        response.setInfoResponses(AppointmentMapper.toInfoResponses(appointments, lastAppointmentsDto));

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/procedures/amount-last-seven-days")
    @Operation(summary = "Lista os últimos 7 dias a quantidade de serviços prestados ")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Map<LocalDate, Long>> getAmountProceduresLastSevenDays() {

        Map<LocalDate, Long> appointmentCountDtos = dashboardService.amountProceduresCountPerDay();
        if(appointmentCountDtos.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(appointmentCountDtos);
    }

    @GetMapping("/procedures/most-performed-last-thirty-days")
    @Operation(summary = "Busca o procedimento mais realizado do mês",
            description = "retorna data de início e fim do mês, o nome do serviço e quantidade de procedimentos realizados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TopServiceResponseDto> getMostPerformedProcedureMonth() {
        TopServiceResponseDto response = dashboardService.mostPerformedProcedureByLastThirtyDays();

        if (response == null)
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/procedures/least-performed-last-thirty-days")
    @Operation(summary = "Busca o procedimento menos realizado do mês",
            description = "retorna data de início e fim do mês, o nome do serviço e quantidade de procedimentos realizados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<LeastServiceResponseDto> getLeastPerformedProcedureMonth() {
        LeastServiceResponseDto response = dashboardService.leastPerformedProcedureByLastThirtyDays();

        if (response == null)
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/procedures/most-procedures-timing-last-thirty-days")
    @Operation(summary = "Busca o horário mais agendado dos últimos 30 dias",
            description = "Retorna a data de 30 dias atrás e o dia atual, o horário mais agendado e a quantidade")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TopProceduresTimingResponse> getMostProceduresTimingByLastThirtyDays() {
        TopProceduresTimingResponse response = dashboardService.mostProceduresTimingByLastThirtyDays();

        if (response == null)
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/procedures/least-procedures-timing-last-thirty-days")
    @Operation(summary = "Busca o horário menos agendado dos últimos 30 dias",
            description = "Retorna a data de 30 dias atrás e o dia atual, o horário menos agendado e a quantidade")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<LeastProceduresTimingResponse> getLeastProceduresTimingByLastThirtyDays() {
        LeastProceduresTimingResponse response = dashboardService.leastProceduresTimingByLastThirtyDays();

        if (response == null)
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(response);
    }


}
