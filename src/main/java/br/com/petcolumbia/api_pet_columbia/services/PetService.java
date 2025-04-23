package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.repositories.IPetRepository;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    private final IPetRepository petRepository;
    private final OwnerService ownerService;

    public PetService(IPetRepository petRepository, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
    }

    public PetModel createPet(Integer ownerId, PetModel pet){
        OwnerModel owner = ownerService.getOwnerById(ownerId);

        pet.setOwner(owner);
        petRepository.save(pet);

        return pet;
    }

    public PetModel findPetById(@PathVariable Integer id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));
    }

    public List<PetModel> listAllPetsByOwner(Integer ownerId) {
        OwnerModel owner = ownerService.getOwnerById(ownerId);

        List<PetModel> pets = petRepository.findAllByOwner(owner);

        if(pets.isEmpty())
            return new ArrayList<>();

        return pets;
    }

    public void deletePetById(Integer id) {
        if(!petRepository.existsById(id))
            throw new EntityNotFoundException("Pet não encontrado");

        petRepository.deleteById(id);
    }

    public PetModel updatePetById(Integer id, PetModel updatedPet) {
        PetModel pet  = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

        pet.setName(updatedPet.getName());
        pet.setSize(updatedPet.getSize());
        pet.setSpecies(updatedPet.getSpecies());
        pet.setBreed(updatedPet.getBreed());
        pet.setCoat(updatedPet.getCoat());
        pet.setAge(updatedPet.getAge());
        pet.setSex(updatedPet.getSex());
        pet.setLastUpdate(LocalDateTime.now());

        petRepository.save(pet);

        return pet;
    }
}
