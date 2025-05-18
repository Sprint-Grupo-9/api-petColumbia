package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos.AppointmentCountDto;
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

    @Query("SELECT new br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos.AppointmentCountDto(DATE(a.startDateTime), COUNT(a)) " +
            "FROM AppointmentModel a " +
            "WHERE a.startDateTime BETWEEN :start AND :end " +
            "GROUP BY DATE(a.startDateTime) " +
            "ORDER BY DATE(a.startDateTime) ASC")
    List<AppointmentCountDto> countAppointmentsGroupedByDay(@Param("start") LocalDateTime start,
                                                            @Param("end") LocalDateTime end);


}
