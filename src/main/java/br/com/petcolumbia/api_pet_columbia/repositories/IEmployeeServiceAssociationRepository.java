package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeServiceAssociationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeServiceAssociationRepository extends JpaRepository<EmployeeServiceAssociationModel, Integer> {

    @Query("SELECT es.employee FROM EmployeeServiceAssociationModel es WHERE es.service.id = :serviceId")
    List<EmployeeModel> findEmployeesByServiceId(@Param("serviceId") Integer serviceId);
}