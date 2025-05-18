package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.AppointmentMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos.AppointmentCountDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos.AppointmentsDashboardInfosResponseDto;
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

@RestController
@RequestMapping("/dashboards")
public class DashboardsController {

    private final DashboardService dashboardService;

    public DashboardsController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/appointments/date")
    @Operation(summary = "Lista todos os agendamentos marcados de uma data, recebe uma data")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AppointmentsDashboardInfosResponseDto> listAppointmentsByDate(@RequestParam LocalDate date){
        List<AppointmentModel> appointments = dashboardService.appointmentsByDate(date);

        if (appointments.isEmpty())
            return ResponseEntity.status(204).build();

        AppointmentsDashboardInfosResponseDto response  = new AppointmentsDashboardInfosResponseDto();

        response.setCardResponses(AppointmentMapper.entitiesToCardInfoResponses(appointments));
        response.setInfoResponses(AppointmentMapper.entitiesToInfoResponses(appointments));

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/procedures /amount-last-seven-days")
    @Operation(summary = "",
            description = "")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AppointmentCountDto>> getAmountProceduresLastSevenDays() {

        List<AppointmentCountDto> appointmentCountDtos = dashboardService.amountProceduresByLastSevenDays();
        if(appointmentCountDtos.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(appointmentCountDtos);
    }

    // Procedimento mais realizado do mes (retornar a data de inicio e fim do mes, o serviço e a quantidade)
    @GetMapping("/procedures/most-performed-month")
    @Operation(summary = "",
            description = "")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<?> getMostPerformedProcedureMonth() {
        return ResponseEntity.ok("Most performed procedure of the month");
    }

    //  procedimento com menor demanda (retornar a data de inicio e fim do mes, serviço e quantidade)
    @GetMapping("/procedures/least-performed-month")
    @Operation(summary = "",
            description = "")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<?> getLeastPerformedProcedureMonth() {
        return ResponseEntity.ok("Least performed procedure of the month");
    }

    /*  Intervalo de maior fluxo de agendamentos no mes (retornar a data de inicio e fim do mes,
    os intervalo e a quantidade de agendamentos entre o intervalo ) */
    @GetMapping("/procedures/busiest-time-interval-month")
    @Operation(summary = "",
            description = "")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<?> getBusiestTimeIntervalInMonth() {
        return ResponseEntity.ok("Interval of more procedures");
    }

    /*Intervalo de menor fluxo de agendamentos no mes (retornar a data de inicio e fim do mes,
        os intervalo e a quantidade de agendamentos entre o intervalo )*/
    @GetMapping("/procedures/quietest-time-interval-month")
    @Operation(summary = "",
            description = "")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<?> getQuietestTimeIntervalInMonth() {
        return ResponseEntity.ok("Interval of less procedures");
    }


}
