package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer> {
    @Query("SELECT es.employee FROM EmployeeServiceAssociationModel es WHERE es.service.id IN :serviceIds")
    List<EmployeeModel> findEmployeesByServiceIds(List<Integer> serviceIds);
}