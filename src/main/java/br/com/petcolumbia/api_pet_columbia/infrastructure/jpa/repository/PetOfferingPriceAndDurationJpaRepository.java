package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository;

import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetOfferingPriceAndDurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetOfferingPriceAndDurationJpaRepository extends JpaRepository<PetOfferingPriceAndDurationEntity, Integer> {

    @Query("SELECT p FROM PetOfferingPriceAndDurationEntity p " +
           "WHERE p.petOffering.id = :petOfferingId " +
           "AND p.petSize = :petSize " +
           "AND p.petCoat = :petCoat")
    PetOfferingPriceAndDurationEntity findByPetOfferingIdAndPetSizeAndPetCoat(
            @Param("petOfferingId") Integer petOfferingId,
            @Param("petSize") String petSize,
            @Param("petCoat") String petCoat
    );
}

