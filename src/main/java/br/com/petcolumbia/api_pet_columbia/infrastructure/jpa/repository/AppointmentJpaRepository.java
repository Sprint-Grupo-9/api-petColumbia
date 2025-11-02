package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository;

import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.AppointmentEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Integer> {

    @Query("SELECT a FROM AppointmentEntity a JOIN FETCH a.pet p JOIN FETCH p.owner WHERE a.pet.owner.id = :ownerId")
    List<AppointmentEntity> findAllAppointmentsByOwnerId(@Param("ownerId") Integer ownerId);

    Page<AppointmentEntity> findAllAppointmentsByOwnerIdPaginated(@Param("ownerId") Integer ownerId, Pageable pageable);

    List<AppointmentEntity> findByEmployeeAndStartDateTimeGreaterThanEqualAndStartDateTimeLessThan(
            EmployeeEntity employee, LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT a FROM AppointmentEntity a JOIN FETCH a.pet p JOIN FETCH p.owner JOIN FETCH a.employee WHERE a.startDateTime BETWEEN :start AND :end")
    List<AppointmentEntity> findByStartDateTimeBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT a.startDateTime FROM AppointmentEntity a WHERE a.startDateTime BETWEEN :start AND :end")
    List<LocalDateTime> findStartTimesBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT a.endDateTime FROM AppointmentEntity a WHERE a.endDateTime BETWEEN :start AND :end")
    List<LocalDateTime> findEndDateTimesBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT a.petOfferings FROM AppointmentEntity a WHERE a.startDateTime BETWEEN :start AND :end")
    List<String> findAllPetOfferingsBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT a FROM AppointmentEntity a JOIN FETCH a.pet p JOIN FETCH p.owner WHERE a.pet.owner.id = :ownerId ORDER BY a.startDateTime DESC")
    List<AppointmentEntity> findTop3ByOwnerIdOrderByStartDateTimeDesc(@Param("ownerId") Integer ownerId, Pageable pageable);

    @Query("SELECT a FROM AppointmentEntity a JOIN FETCH a.pet p JOIN FETCH p.owner WHERE a.pet.id = :petId ORDER BY a.startDateTime DESC")
    List<AppointmentEntity> findTop3ByPetIdOrderByStartDateTimeDesc(@Param("petId") Integer petId, Pageable pageable);
}

