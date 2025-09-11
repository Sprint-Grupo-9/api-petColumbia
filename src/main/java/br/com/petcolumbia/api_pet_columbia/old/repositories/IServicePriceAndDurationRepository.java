package br.com.petcolumbia.api_pet_columbia.old.repositories;

import br.com.petcolumbia.api_pet_columbia.old.domain.entities.ServicePriceAndDurationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicePriceAndDurationRepository extends JpaRepository<ServicePriceAndDurationModel, Integer> {
    @Query("SELECT  s FROM  ServicePriceAndDurationModel  s WHERE s.service.id  = :serviceId AND s.petSize = :petSize AND s.petCoat = :petCoat")
    ServicePriceAndDurationModel findByIdAndPetSizeAndPetCoat(
            @Param("serviceId") Integer serviceId, @Param("petSize") String petSize, @Param("petCoat") String petCoat);
}
