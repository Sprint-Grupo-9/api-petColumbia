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
        List<LastAppointmentsListDto> lastPetAppointments = getPetLastAppointments(appointments);
        List<LastAppointmentsListDto> lastOwnerAppointments = getOwnerLastAppointments(appointments);

        return new LastPetAndOwnerAppointmentsResponseDto(lastPetAppointments, lastOwnerAppointments);
    }

    @Override
    public Map<LocalDate, Long> getAmountPetOfferingsCountPerDay() {
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
    public TopPetOfferingResponseDto getMostPerformedPetOfferingLastThirtyDays() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.minusDays(1).withHour(23).withMinute(59);
        LocalDateTime start = end.minusDays(29).withHour(0).withMinute(0);

        Map<String, Long> petOfferingCount = countPetOfferingsByLastThirtyDays();

        Map.Entry<String, Long> top = petOfferingCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        if (top == null) return null;

        return new TopPetOfferingResponseDto(top.getKey(), top.getValue(), start.toLocalDate(), end.toLocalDate());
    }

    @Override
    public LeastPetOfferingResponseDto getLeastPerformedPetOfferingLastThirtyDays() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.minusDays(1).withHour(23).withMinute(59);
        LocalDateTime start = end.minusDays(29).withHour(0).withMinute(0);

        Map<String, Long> petOfferingCount = countPetOfferingsByLastThirtyDays();

        Map.Entry<String, Long> least = petOfferingCount.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);

        if (least == null) return null;

        return new LeastPetOfferingResponseDto(least.getKey(), least.getValue(), start.toLocalDate(), end.toLocalDate());
    }

    @Override
    public TopPetOfferingsTimingResponse getMostPetOfferingsTimingLastThirtyDays() {
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

        return new TopPetOfferingsTimingResponse(start.toLocalDate(), end.toLocalDate(), time, count);
    }

    @Override
    public LeastPetOfferingsTimingResponse getLeastPetOfferingsTimingLastThirtyDays() {
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

        return new LeastPetOfferingsTimingResponse(start.toLocalDate(), end.toLocalDate(), time, count);
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
                            a.getPetOfferings(),
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
                            a.getPetOfferings(),
                            a.getTotalPrice()
                    )
            ).toList();

            response.add(new LastAppointmentsListDto(dtoList));
        }
        return response;
    }

    private Map<String, Long> countPetOfferingsByLastThirtyDays() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.minusDays(1).withHour(23).withMinute(59);
        LocalDateTime start = end.minusDays(29).withHour(0).withMinute(0);

        List<String> allStringPetOfferings = appointmentRepository.findAllPetOfferingsBetween(start, end);

        Map<String, Long> petOfferingCount = new HashMap<>();

        for (String petOfferingLine : allStringPetOfferings) {
            if (petOfferingLine == null || petOfferingLine.isBlank()) continue;

            String[] petOfferings = petOfferingLine.split(",");

            for (String petOffering : petOfferings) {
                petOffering = petOffering.trim();
                petOfferingCount.put(petOffering, petOfferingCount.getOrDefault(petOffering, 0L) + 1);
            }
        }

        return petOfferingCount;
    }
}

