package br.com.petcolumbia.api_pet_columbia.Repositories;
import br.com.petcolumbia.api_pet_columbia.Models.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPetRepository extends JpaRepository<PetModel, Integer> {

}


