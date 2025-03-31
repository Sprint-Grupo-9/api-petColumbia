package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.repositories.IPetRepository;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private IPetRepository petRepository;

    public PetModel createPet(PetModel newPet){
        return petRepository.save(newPet);
    }

    public PetModel findyPetById(@PathVariable Integer id) {
        Optional<PetModel> byId = petRepository.findById(id);

        if(byId.isEmpty())
            throw new EntityNotFoundException();

        return byId.get();
    }

    public void deletePetById(Integer id) {
        if(!petRepository.existsById(id))
            throw new EntityNotFoundException();

        petRepository.deleteById(id);
    }

    public PetModel updatePetById(Integer id, PetModel pet) {
        if(!petRepository.existsById(id))
            throw new EntityNotFoundException();

        pet.setId(id);
        return petRepository.save(pet);
    }
}
