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
    private final GetAmountPetOfferingsLastSevenDaysUseCase getAmountPetOfferingsLastSevenDaysUseCase;
    private final GetMostPerformedPetOfferingsUseCase getMostPerformedPetOfferingsUseCase;
    private final GetLeastPerformedPetOfferingsUseCase getLeastPerformedPetOfferingsUseCase;
    private final GetMostPetOfferingsTimingUseCase getMostPetOfferingsTimingUseCase;
    private final GetLeastPetOfferingsTimingUseCase getLeastPetOfferingsTimingUseCase;

    public DashboardController(
            GetAppointmentsByDateUseCase getAppointmentsByDateUseCase,
            GetAmountPetOfferingsLastSevenDaysUseCase getAmountPetOfferingsLastSevenDaysUseCase,
            GetMostPerformedPetOfferingsUseCase getMostPerformedPetOfferingsUseCase,
            GetLeastPerformedPetOfferingsUseCase getLeastPerformedPetOfferingsUseCase,
            GetMostPetOfferingsTimingUseCase getMostPetOfferingsTimingUseCase,
            GetLeastPetOfferingsTimingUseCase getLeastPetOfferingsTimingUseCase
    ) {
        this.getAppointmentsByDateUseCase = getAppointmentsByDateUseCase;
        this.getAmountPetOfferingsLastSevenDaysUseCase = getAmountPetOfferingsLastSevenDaysUseCase;
        this.getMostPerformedPetOfferingsUseCase = getMostPerformedPetOfferingsUseCase;
        this.getLeastPerformedPetOfferingsUseCase = getLeastPerformedPetOfferingsUseCase;
        this.getMostPetOfferingsTimingUseCase = getMostPetOfferingsTimingUseCase;
        this.getLeastPetOfferingsTimingUseCase = getLeastPetOfferingsTimingUseCase;
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

    @GetMapping("/pet-offerings/amount-last-seven-days")
    @Operation(summary = "Lista os últimos 7 dias a quantidade de serviços prestados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Map<LocalDate, Long>> getAmountPetOfferingsLastSevenDays() {
        Map<LocalDate, Long> appointmentCounts = getAmountPetOfferingsLastSevenDaysUseCase.execute();

        if (appointmentCounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(appointmentCounts);
    }

    @GetMapping("/pet-offerings/most-performed-last-thirty-days")
    @Operation(summary = "Busca o serviço mais realizado do mês",
            description = "Retorna data de início e fim do mês, o nome do serviço e quantidade realizados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TopPetOfferingResponseDto> getMostPerformedPetOfferingMonth() {
        TopPetOfferingResponseDto response = getMostPerformedPetOfferingsUseCase.execute();

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/pet-offerings/least-performed-last-thirty-days")
    @Operation(summary = "Busca o serviço menos realizado do mês",
            description = "Retorna data de início e fim do mês, o nome do serviço e quantidade realizados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<LeastPetOfferingResponseDto> getLeastPerformedPetOfferingMonth() {
        LeastPetOfferingResponseDto response = getLeastPerformedPetOfferingsUseCase.execute();

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/pet-offerings/most-timing-last-thirty-days")
    @Operation(summary = "Busca o horário mais agendado dos últimos 30 dias",
            description = "Retorna a data de 30 dias atrás e o dia atual, o horário mais agendado e a quantidade")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TopPetOfferingsTimingResponse> getMostPetOfferingsTimingByLastThirtyDays() {
        TopPetOfferingsTimingResponse response = getMostPetOfferingsTimingUseCase.execute();

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/pet-offerings/least-timing-last-thirty-days")
    @Operation(summary = "Busca o horário menos agendado dos últimos 30 dias",
            description = "Retorna a data de 30 dias atrás e o dia atual, o horário menos agendado e a quantidade")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<LeastPetOfferingsTimingResponse> getLeastPetOfferingsTimingByLastThirtyDays() {
        LeastPetOfferingsTimingResponse response = getLeastPetOfferingsTimingUseCase.execute();

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }
}

