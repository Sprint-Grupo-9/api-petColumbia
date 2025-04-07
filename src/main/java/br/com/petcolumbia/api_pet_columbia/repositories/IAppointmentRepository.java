package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<AppointmentModel, Integer> {
    @Query(""" 
        SELECT a.startDateTime, a.endDateTime
        FROM AppointmentModel a
        WHERE a.employee = :employee
        AND a.startDateTime >= :startOfDay
        AND a.startDateTime < :endOfDay
    """)
    List<AppointmentModel> findBusyTimesByEmployeeAndDate(
            EmployeeModel employee, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
