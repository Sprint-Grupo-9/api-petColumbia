package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<AppointmentModel, Integer> {
    List<AppointmentModel> findByEmployeeAndStartDateTimeGreaterThanEqualAndStartDateTimeLessThan(
            EmployeeModel employee, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<AppointmentModel> findByStartDateTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT  a.startDateTime FROM  AppointmentModel  a WHERE  a.startDateTime BETWEEN  :start AND  :end")
    List<LocalDateTime> findStartTimesBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT a.endDateTime " +
            "FROM AppointmentModel a " +
            "WHERE a.endDateTime BETWEEN :start AND :end")
    List<LocalDateTime> findEndDateTimesBetween(@Param("start") LocalDateTime start,
                                                @Param("end") LocalDateTime end);

    @Query("SELECT a.services FROM AppointmentModel a WHERE a.startDateTime BETWEEN :start AND :end")
    List<String> findAllServicesBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT a FROM AppointmentModel a WHERE a.pet.owner.id = :ownerId ORDER BY a.startDateTime DESC")
    List<AppointmentModel> findTop3ByOwnerIdOrderByStartDateTimeDesc(@Param("ownerId") Integer ownerId, Pageable pageable);

    @Query("SELECT a FROM AppointmentModel a WHERE a.pet.id = :petId ORDER BY a.startDateTime DESC")
    List<AppointmentModel> findTop3ByPetIdOrderByStartDateTimeDesc(@Param("petId") Integer petId, Pageable pageable);

}
