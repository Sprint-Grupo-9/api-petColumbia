package br.com.petcolumbia.api_pet_columbia.repositories;
import br.com.petcolumbia.api_pet_columbia.models.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPetRepository extends JpaRepository<PetModel, Integer> {

}


