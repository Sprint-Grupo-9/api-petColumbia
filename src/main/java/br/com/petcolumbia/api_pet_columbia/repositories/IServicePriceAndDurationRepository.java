package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.ServicePriceAndDurationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicePriceAndDurationRepository extends JpaRepository<ServicePriceAndDurationModel, Integer> {
    ServicePriceAndDurationModel findByIdAndPetSizeAndPetCoat(Integer serviceId, String petSize, String petCoat);
}
