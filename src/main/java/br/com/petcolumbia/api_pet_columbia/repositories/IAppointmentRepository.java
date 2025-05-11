package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<AppointmentModel, Integer> {
    List<AppointmentModel> findByEmployeeAndStartDateTimeGreaterThanEqualAndStartDateTimeLessThan(
            EmployeeModel employee, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<AppointmentModel> findByStartDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
