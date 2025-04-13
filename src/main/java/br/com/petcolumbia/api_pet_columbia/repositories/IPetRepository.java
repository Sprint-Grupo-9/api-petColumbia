package br.com.petcolumbia.api_pet_columbia.repositories;
import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPetRepository extends JpaRepository<PetModel, Integer> {
    List<PetModel> findAllByOwner(OwnerModel owner);
}


