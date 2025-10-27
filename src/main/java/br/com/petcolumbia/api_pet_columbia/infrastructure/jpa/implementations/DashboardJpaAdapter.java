package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.implementations;

import br.com.petcolumbia.api_pet_columbia.core.adapter.dashboard.DashboardGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard.*;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.AppointmentEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.AppointmentEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.AppointmentJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardJpaAdapter implements DashboardGateway {

    private final AppointmentJpaRepository appointmentRepository;

    public DashboardJpaAdapter(AppointmentJpaRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> findAppointmentsByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        List<AppointmentEntity> entities = appointmentRepository.findByStartDateTimeBetween(startOfDay, endOfDay);

        MappingContext context = new MappingContext(new MappingStrategy.AppointmentFullMapping());
        return AppointmentEntityMapper.toDomainList(entities, context);
    }

    @Override
    public LastPetAndOwnerAppointmentsResponseDto getLastAppointmentsOfPetAndOwner(List<Appointment> appointments) {
        List<LastAppointmentsListDto> lastPetAppointments = getOwnerLastAppointments(appointments);
        List<LastAppointmentsListDto> lastOwnerAppointments = getPetLastAppointments(appointments);

        return new LastPetAndOwnerAppointmentsResponseDto(lastPetAppointments, lastOwnerAppointments);
    }

    @Override
    public Map<LocalDate, Long> getAmountProceduresCountPerDay() {
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

    @Override
    public TopServiceResponseDto getMostPerformedProcedureLastThirtyDays() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.minusDays(1).withHour(23).withMinute(59);
        LocalDateTime start = end.minusDays(29).withHour(0).withMinute(0);

        Map<String, Long> serviceCount = countProceduresByLastThirtyDays();

        Map.Entry<String, Long> top = serviceCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (top == null) return null;

        return new TopServiceResponseDto(top.getKey(), top.getValue(), start.toLocalDate(), end.toLocalDate());
    }

    @Override
    public LeastServiceResponseDto getLeastPerformedProcedureLastThirtyDays() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.minusDays(1).withHour(23).withMinute(59);
        LocalDateTime start = end.minusDays(29).withHour(0).withMinute(0);

        Map<String, Long> serviceCount = countProceduresByLastThirtyDays();

        Map.Entry<String, Long> least = serviceCount.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);

        if (least == null) return null;

        return new LeastServiceResponseDto(least.getKey(), least.getValue(), start.toLocalDate(), end.toLocalDate());
    }

    @Override
    public TopProceduresTimingResponse getMostProceduresTimingLastThirtyDays() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusDays(30);

        List<LocalDateTime> startTimes = appointmentRepository.findStartTimesBetween(start, end);

        Map<LocalTime, Long> timeCountMap = new HashMap<>();

        for (LocalDateTime dateTime : startTimes) {
            LocalTime time = dateTime.toLocalTime().withSecond(0).withNano(0);
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

    @Override
    public LeastProceduresTimingResponse getLeastProceduresTimingLastThirtyDays() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusDays(30);

        List<LocalDateTime> startTimes = appointmentRepository.findStartTimesBetween(start, end);

        Map<LocalTime, Long> timeCountMap = new HashMap<>();

        for (LocalDateTime dateTime : startTimes) {
            LocalTime time = dateTime.toLocalTime().withSecond(0).withNano(0);
            timeCountMap.put(time, timeCountMap.getOrDefault(time, 0L) + 1);
        }

        Optional<Map.Entry<LocalTime, Long>> leastCommon = timeCountMap.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue());

        if (leastCommon.isEmpty())
            return null;

        LocalTime time = leastCommon.get().getKey();
        long count = leastCommon.get().getValue();

        return new LeastProceduresTimingResponse(start.toLocalDate(), end.toLocalDate(), time, count);
    }

    // Helper methods
    private List<LastAppointmentsListDto> getOwnerLastAppointments(List<Appointment> appointments) {
        PageRequest pageRequest = PageRequest.of(0, 3);

        Map<Integer, List<AppointmentEntity>> ownerCache = new HashMap<>();
        List<LastAppointmentsListDto> response = new ArrayList<>();

        for (Appointment appointment : appointments) {
            Integer ownerId = appointment.getPet().getOwner().getId();

            List<AppointmentEntity> last3;
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
                            a.getProcedures(),
                            a.getTotalPrice()
                    )
            ).toList();

            response.add(new LastAppointmentsListDto(dtoList));
        }
        return response;
    }

    private List<LastAppointmentsListDto> getPetLastAppointments(List<Appointment> appointments) {
        PageRequest pageRequest = PageRequest.of(0, 3);

        Map<Integer, List<AppointmentEntity>> petCache = new HashMap<>();
        List<LastAppointmentsListDto> response = new ArrayList<>();

        for (Appointment appointment : appointments) {
            Integer petId = appointment.getPet().getId();

            List<AppointmentEntity> last3;
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
                            a.getProcedures(),
                            a.getTotalPrice()
                    )
            ).toList();

            response.add(new LastAppointmentsListDto(dtoList));
        }
        return response;
    }

    private Map<String, Long> countProceduresByLastThirtyDays() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.minusDays(1).withHour(23).withMinute(59);
        LocalDateTime start = end.minusDays(29).withHour(0).withMinute(0);

        List<String> allStringProcedures = appointmentRepository.findAllProceduresBetween(start, end);

        Map<String, Long> procedureCount = new HashMap<>();

        for (String procedureLine : allStringProcedures) {
            if (procedureLine == null || procedureLine.isBlank()) continue;

            String[] procedures = procedureLine.split(",");

            for (String procedure : procedures) {
                procedure = procedure.trim();
                procedureCount.put(procedure, procedureCount.getOrDefault(procedure, 0L) + 1);
            }
        }

        return procedureCount;
    }
}

