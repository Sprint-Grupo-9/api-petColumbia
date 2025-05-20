package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
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

    @Query("SELECT FUNCTION('date', a.endDateTime) AS date, COUNT(a) AS count " +
            "FROM AppointmentModel a " +
            "WHERE a.endDateTime BETWEEN :start AND :end " +
            "GROUP BY FUNCTION('date', a.endDateTime) " +
            "ORDER BY FUNCTION('date', a.endDateTime) ASC")
    List<Object[]> countAppointmentsGroupedByDay(@Param("start") LocalDateTime start,
                                                 @Param("end") LocalDateTime end);



}
