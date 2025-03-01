package br.com.petcolumbia.api_pet_columbia.Controllers;

import br.com.petcolumbia.api_pet_columbia.Models.PetModel;
import br.com.petcolumbia.api_pet_columbia.Repositories.Interfaces.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    private IPetRepository repository;

    @PostMapping
    public ResponseEntity<PetModel> registerPet(@RequestBody PetModel newPet){
        if (newPet == null)
            return ResponseEntity.status(400).build();

        PetModel savedPet = repository.save(newPet);

        return ResponseEntity.status(201).body(savedPet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetModel> getPetById(@PathVariable Integer id){
        Optional<PetModel> pet = repository.findById(id);

        if (pet.isPresent())
            return ResponseEntity.status(200).body(pet.get());

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetModel> updatePetByID(@PathVariable Integer id, @RequestBody PetModel petToUpdate){

        if(repository.existsById(id)){
            petToUpdate.setId(id);
            PetModel updatedPet = repository.save(petToUpdate);
            return ResponseEntity.status(200).body(updatedPet);
        }

        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetById(@PathVariable Integer id) {

        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
            return ResponseEntity.status(404).build();
    }

}
