package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.dashboard.LeastServiceResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.dashboard.TopServiceResponseDto;
import br.com.petcolumbia.api_pet_columbia.repositories.IAppointmentRepository;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final IAppointmentRepository appointmentRepository;

    public DashboardService(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<AppointmentModel> appointmentsByDate(LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59);

        return appointmentRepository.findByStartDateTimeBetween(startOfDay, endOfDay);
    }

    public Map<LocalDate, Long> amountProceduresCountPerDay() {
        LocalDateTime start = LocalDate.now().minusDays(7) .atTime(0, 0);
        LocalDateTime end = LocalDateTime.now();

        List<Object[]> resultQuery = appointmentRepository.countAppointmentsGroupedByDay(start, end);

        Map<LocalDate, Long> map = resultQuery.stream()
                .collect(Collectors.toMap(
                        row -> ((java.sql.Date) row[0]).toLocalDate(),
                        row -> (Long) row[1]
                ));

        Map<LocalDate, Long> resultMap = new LinkedHashMap<>();

        LocalDate current = start.toLocalDate();

        // Complentando caso tenha dias com zero agendamentos
        while (!current.isAfter(end.toLocalDate())) {
            resultMap.put(current, map.getOrDefault(current, 0L));
            current = current.plusDays(1);
        }

        return resultMap;
    }

    public Map<String, Long> countServicesByLastThirtyDays(){
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime end = now.minusDays(1).withHour(23).withMinute(59);

        LocalDateTime start = end.minusDays(29).withHour(0).withMinute(0);

        // Retornando apenas as strings das tuplas
        List<String> allStringServices = appointmentRepository.findAllServicesBetween(start, end);

        Map<String, Long> serviceCount = new HashMap<>();

        for (String serviceLine : allStringServices) {
            if (serviceLine == null || serviceLine.isBlank()) continue;

            String[] services = serviceLine.split(",");


            for (String service : services) {
                service = service.trim();
                serviceCount.put(service, serviceCount.getOrDefault(service, 0L) + 1);
            }
        }

        return serviceCount;
    }

    public TopServiceResponseDto mostPerformedProcedureByLastThirtyDays() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime end = now.minusDays(1).withHour(23).withMinute(59);

        LocalDateTime start = end.minusDays(29).withHour(0).withMinute(0);

        Map<String, Long> serviceCount =  countServicesByLastThirtyDays();

        Map.Entry<String, Long> top = serviceCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (top == null) return null;

        return new TopServiceResponseDto(top.getKey(), top.getValue(), start.toLocalDate(), end.toLocalDate());
    }

    public LeastServiceResponseDto leastPerformedProcedureByLastThirtyDays() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime end = now.minusDays(1).withHour(23).withMinute(59);

        LocalDateTime start = end.minusDays(29).withHour(0).withMinute(0);

        Map<String, Long> serviceCount =  countServicesByLastThirtyDays();

        Map.Entry<String, Long> top = serviceCount.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);

        if (top == null) return null;

        return new LeastServiceResponseDto(top.getKey(), top.getValue(), start.toLocalDate(), end.toLocalDate());
    }

    /*  Intervalo de maior fluxo de agendamentos no mes (retornar a data de inicio e fim do mes,
    os intervalo e a quantidade de agendamentos entre o intervalo ) */
    public ResponseEntity<?> busiestTimeIntervalInActualMonth() {
        return ResponseEntity.ok("Interval of more procedures");
    }

    /*Intervalo de menor fluxo de agendamentos no mes (retornar a data de inicio e fim do mes,
        os intervalo e a quantidade de agendamentos entre o intervalo )*/
    public ResponseEntity<?> quietestTimeIntervalInActualMonth() {
        return ResponseEntity.ok("Interval of less procedures");
    }
}
