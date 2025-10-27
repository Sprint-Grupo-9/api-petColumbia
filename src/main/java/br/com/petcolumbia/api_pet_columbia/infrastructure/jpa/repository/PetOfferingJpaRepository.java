package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository;

import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetOfferingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetOfferingJpaRepository extends JpaRepository<PetOfferingEntity, Integer> {
}

