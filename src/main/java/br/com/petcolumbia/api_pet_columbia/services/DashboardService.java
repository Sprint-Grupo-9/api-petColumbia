package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.repositories.IAppointmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


    // Procedimento mais realizado do mes (retornar a data de inicio e fim do mes, o serviço e a quantidade)
    public ResponseEntity<?> mostPerformedProcedureByActualMonth() {
        return ResponseEntity.ok("Most performed procedure of the month");
    }

    //  procedimento com menor demanda (retornar a data de inicio e fim do mes, serviço e quantidade)
    public ResponseEntity<?> leastPerformedProcedureByActualMonth() {
        return ResponseEntity.ok("Least performed procedure of the month");
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
