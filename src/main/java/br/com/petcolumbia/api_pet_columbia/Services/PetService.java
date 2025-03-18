package br.com.petcolumbia.api_pet_columbia.Services;

import br.com.petcolumbia.api_pet_columbia.Models.PetModel;
import br.com.petcolumbia.api_pet_columbia.Repositories.IPetRepository;
import br.com.petcolumbia.api_pet_columbia.Repositories.IUserRepository;
import br.com.petcolumbia.api_pet_columbia.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private IPetRepository repository;

    public PetModel createPet(PetModel newPet){
        return repository.save(newPet);
    }

    public PetModel findyPetById(@PathVariable Integer id) {
        Optional<PetModel> byId = repository.findById(id);

        if(byId.isEmpty())
            throw new EntityNotFoundException();

        return byId.get();
    }

    public void deletePetById(Integer id) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        repository.deleteById(id);
    }

    public PetModel updatePetById(Integer id, PetModel pet) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        pet.setId(id);
        return repository.save(pet);
    }
}
