package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository;

import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Integer> {

    @Query("SELECT ep.employee FROM EmployeePetOfferingAssociationEntity ep " +
           "WHERE ep.petOffering.id IN :petOfferingIds " +
           "GROUP BY ep.employee " +
           "HAVING COUNT(DISTINCT ep.petOffering.id) = :petOfferingCount")
    List<EmployeeEntity> findEmployeesByPetOfferingIds(
        @Param("petOfferingIds") List<Integer> petOfferingIds,
        @Param("petOfferingCount") long petOfferingCount
    );
}

