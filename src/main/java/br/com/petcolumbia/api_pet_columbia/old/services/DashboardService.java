package br.com.petcolumbia.api_pet_columbia.old.services;

import br.com.petcolumbia.api_pet_columbia.old.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.dashboard.*;
import br.com.petcolumbia.api_pet_columbia.old.repositories.IAppointmentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final IAppointmentRepository appointmentRepository;

    public DashboardService(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public LastPetAndOwnerAppointmentsResponseDto lastAppointmentsOfPetAndOwnerByAppointmentsList(List<AppointmentModel> appointments) {
        List<LastAppointmentsListDto> lastPetAppointments = ownerLastAppointments(appointments);
        List<LastAppointmentsListDto> lastOwnerAppointments = petLastAppointments(appointments);

        return new LastPetAndOwnerAppointmentsResponseDto(lastPetAppointments, lastOwnerAppointments);
    }

    //Últimos 3 agendamentos de todos os usuários dos agendamentos passados como parâmetro
    public List<LastAppointmentsListDto> ownerLastAppointments(List<AppointmentModel> appointments) {
        PageRequest pageRequest = PageRequest.of(0, 3);

        Map<Integer, List<AppointmentModel>> ownerCache = new HashMap<>();
        List<LastAppointmentsListDto> response = new ArrayList<>();

        for (AppointmentModel appointment : appointments) {
            Integer ownerId = appointment.getPet().getOwner().getId();

            // Cache para evitar ir no banco quando pet for repetido
            List<AppointmentModel> last3;
            if (ownerCache.containsKey(ownerId)) {
                last3 = ownerCache.get(ownerId);
            } else {
                last3 = appointmentRepository.findTop3ByOwnerIdOrderByStartDateTimeDesc(ownerId, pageRequest);
                ownerCache.put(ownerId, last3);
            }

            List<LastAppointmentsDto> dtoList = last3.stream().map(a ->
                    new LastAppointmentsDto(
                            ownerId,
                            a.getId(),
                            a.getStartDateTime().toLocalDate(),
                            a.getStartDateTime().toLocalTime(),
                            a.getEndDateTime().toLocalTime(),
                            a.getServices(),
                            a.getTotalPrice()
                    )
            ).toList();

            response.add(new LastAppointmentsListDto(dtoList));
        }
        return response;
    }

    //Últimos 3 agendamentos de todos os pets dos agendamentos passados como parâmetro
    public List<LastAppointmentsListDto> petLastAppointments(List<AppointmentModel> appointments){
        PageRequest pageRequest = PageRequest.of(0, 3);

        Map<Integer, List<AppointmentModel>> petCache = new HashMap<>();
        List<LastAppointmentsListDto> response = new ArrayList<>();

        for (AppointmentModel appointment : appointments) {
            Integer petId = appointment.getPet().getId();

            // Cache para evitar ir no banco quando pet for repetido
            List<AppointmentModel> last3;
            if (petCache.containsKey(petId)) {
                last3 = petCache.get(petId);
            } else {
                last3 = appointmentRepository.findTop3ByPetIdOrderByStartDateTimeDesc(petId, pageRequest);
                petCache.put(petId, last3);
            }

            List<LastAppointmentsDto> dtoList = last3.stream().map(a ->
                    new LastAppointmentsDto(
                            petId,
                            a.getId(),
                            a.getStartDateTime().toLocalDate(),
                            a.getStartDateTime().toLocalTime(),
                            a.getEndDateTime().toLocalTime(),
                            a.getServices(),
                            a.getTotalPrice()
                    )
            ).toList();

            response.add(new LastAppointmentsListDto(dtoList));
        }
        return response;
    }

    public List<AppointmentModel> appointmentsByDate(LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59);

        return appointmentRepository.findByStartDateTimeBetween(startOfDay, endOfDay);
    }

    public Map<LocalDate, Long> amountProceduresCountPerDay() {
        LocalDateTime start = LocalDate.now().minusDays(7).atStartOfDay();
        LocalDateTime end = LocalDate.now().minusDays(1).atTime(23, 59, 59);

        List<LocalDateTime> endDateTimes = appointmentRepository.findEndDateTimesBetween(start, end);

        Map<LocalDate, Long> countsByDate = endDateTimes.stream()
                .collect(Collectors.groupingBy(
                        LocalDateTime::toLocalDate,
                        Collectors.counting()
                ));

        Map<LocalDate, Long> resultMap = new LinkedHashMap<>();
        LocalDate current = start.toLocalDate();

        while (!current.isAfter(end.toLocalDate())) {
            resultMap.put(current, countsByDate.getOrDefault(current, 0L));
            current = current.plusDays(1);
        }

        return resultMap;
    }

    public Map<String, Long> countServicesByLastThirtyDays(){
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime end = now.minusDays(1).withHour(23).withMinute(59);

        LocalDateTime start = end.minusDays(29).withHour(0).withMinute(0);

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

    public TopProceduresTimingResponse mostProceduresTimingByLastThirtyDays() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusDays(30);

        List<LocalDateTime> startTimes = appointmentRepository.findStartTimesBetween(start, end);

        Map<LocalTime, Long> timeCountMap  = new HashMap<>();

        for (LocalDateTime dateTime : startTimes) {
            LocalTime time = dateTime.toLocalTime().withSecond(0).withNano(0); // remove segundos/nanos
            timeCountMap.put(time, timeCountMap.getOrDefault(time, 0L) + 1);
        }

        Optional<Map.Entry<LocalTime, Long>> mostCommon = timeCountMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        if (mostCommon.isEmpty())
            return null;

        LocalTime time = mostCommon.get().getKey();
        long count = mostCommon.get().getValue();

        return new TopProceduresTimingResponse(start.toLocalDate(), end.toLocalDate(), time, count);
    }

    public LeastProceduresTimingResponse leastProceduresTimingByLastThirtyDays() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusDays(30);

        List<LocalDateTime> startTimes = appointmentRepository.findStartTimesBetween(start, end);

        Map<LocalTime, Long> timeCountMap  = new HashMap<>();

        for (LocalDateTime dateTime : startTimes) {
            LocalTime time = dateTime.toLocalTime().withSecond(0).withNano(0); // remove segundos/nanos
            timeCountMap.put(time, timeCountMap.getOrDefault(time, 0L) + 1);
        }

        Optional<Map.Entry<LocalTime, Long>> mostCommon = timeCountMap.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue());

        if (mostCommon.isEmpty())
            return null;

        LocalTime time = mostCommon.get().getKey();
        long count = mostCommon.get().getValue();

        return new LeastProceduresTimingResponse(start.toLocalDate(), end.toLocalDate(), time, count);
    }
}
