package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.PriceAndTimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPriceAndTimeRepository extends JpaRepository<PriceAndTimeModel, Integer> {
    PriceAndTimeModel findByIdAndPetSizeAndPetCoat(Integer serviceId, String petSize, String petCoat);
}
