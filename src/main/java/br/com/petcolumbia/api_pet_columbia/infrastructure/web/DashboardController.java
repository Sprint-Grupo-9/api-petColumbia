package br.com.petcolumbia.api_pet_columbia.infrastructure.web;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.*;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.dashboard.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboards")
public class DashboardController {

    private final GetAppointmentsByDateUseCase getAppointmentsByDateUseCase;
    private final GetAmountProceduresLastSevenDaysUseCase getAmountProceduresLastSevenDaysUseCase;
    private final GetMostPerformedProcedureUseCase getMostPerformedProcedureUseCase;
    private final GetLeastPerformedProcedureUseCase getLeastPerformedProcedureUseCase;
    private final GetMostProceduresTimingUseCase getMostProceduresTimingUseCase;
    private final GetLeastProceduresTimingUseCase getLeastProceduresTimingUseCase;

    public DashboardController(
            GetAppointmentsByDateUseCase getAppointmentsByDateUseCase,
            GetAmountProceduresLastSevenDaysUseCase getAmountProceduresLastSevenDaysUseCase,
            GetMostPerformedProcedureUseCase getMostPerformedProcedureUseCase,
            GetLeastPerformedProcedureUseCase getLeastPerformedProcedureUseCase,
            GetMostProceduresTimingUseCase getMostProceduresTimingUseCase,
            GetLeastProceduresTimingUseCase getLeastProceduresTimingUseCase
    ) {
        this.getAppointmentsByDateUseCase = getAppointmentsByDateUseCase;
        this.getAmountProceduresLastSevenDaysUseCase = getAmountProceduresLastSevenDaysUseCase;
        this.getMostPerformedProcedureUseCase = getMostPerformedProcedureUseCase;
        this.getLeastPerformedProcedureUseCase = getLeastPerformedProcedureUseCase;
        this.getMostProceduresTimingUseCase = getMostProceduresTimingUseCase;
        this.getLeastProceduresTimingUseCase = getLeastProceduresTimingUseCase;
    }

    @GetMapping("/appointments/date")
    @Operation(summary = "Lista todos os agendamentos marcados de uma data",
               description = "Recebe uma data e retorna informações do dashboard")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AppointmentsDashboardInfosResponseDto> listAppointmentsByDate(
            @RequestParam LocalDate date
    ) {
        AppointmentsDashboardInfosResponseDto response = getAppointmentsByDateUseCase.execute(date);

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/procedures/amount-last-seven-days")
    @Operation(summary = "Lista os últimos 7 dias a quantidade de procedimentos prestados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Map<LocalDate, Long>> getAmountProceduresLastSevenDays() {
        Map<LocalDate, Long> appointmentCounts = getAmountProceduresLastSevenDaysUseCase.execute();

        if (appointmentCounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(appointmentCounts);
    }

    @GetMapping("/procedures/most-performed-last-thirty-days")
    @Operation(summary = "Busca o procedimento mais realizado do mês",
            description = "Retorna data de início e fim do mês, o nome do procedimento e quantidade realizados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TopServiceResponseDto> getMostPerformedProcedureMonth() {
        TopServiceResponseDto response = getMostPerformedProcedureUseCase.execute();

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/procedures/least-performed-last-thirty-days")
    @Operation(summary = "Busca o procedimento menos realizado do mês",
            description = "Retorna data de início e fim do mês, o nome do procedimento e quantidade realizados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<LeastServiceResponseDto> getLeastPerformedProcedureMonth() {
        LeastServiceResponseDto response = getLeastPerformedProcedureUseCase.execute();

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/procedures/most-procedures-timing-last-thirty-days")
    @Operation(summary = "Busca o horário mais agendado dos últimos 30 dias",
            description = "Retorna a data de 30 dias atrás e o dia atual, o horário mais agendado e a quantidade")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TopProceduresTimingResponse> getMostProceduresTimingByLastThirtyDays() {
        TopProceduresTimingResponse response = getMostProceduresTimingUseCase.execute();

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/procedures/least-procedures-timing-last-thirty-days")
    @Operation(summary = "Busca o horário menos agendado dos últimos 30 dias",
            description = "Retorna a data de 30 dias atrás e o dia atual, o horário menos agendado e a quantidade")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<LeastProceduresTimingResponse> getLeastProceduresTimingByLastThirtyDays() {
        LeastProceduresTimingResponse response = getLeastProceduresTimingUseCase.execute();

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }
}

