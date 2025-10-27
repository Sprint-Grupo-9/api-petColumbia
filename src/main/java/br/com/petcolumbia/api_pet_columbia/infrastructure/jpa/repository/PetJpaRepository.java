package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository;

import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.OwnerEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetJpaRepository extends JpaRepository<PetEntity, Integer> {
    List<PetEntity> findAllByOwner(OwnerEntity owner);
}

