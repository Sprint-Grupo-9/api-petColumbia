package br.com.petcolumbia.api_pet_columbia.old.repositories;
import br.com.petcolumbia.api_pet_columbia.old.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.old.domain.entities.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPetRepository extends JpaRepository<PetModel, Integer> {
    List<PetModel> findAllByOwner(OwnerModel owner);
}


