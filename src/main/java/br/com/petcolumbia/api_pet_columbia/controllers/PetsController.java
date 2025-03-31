package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.services.PetService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetsController {

    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetModel>registerPet(@RequestBody PetModel newPet){
        PetModel savedPet = petService.createPet(newPet);
        return ResponseEntity.status(201).body(savedPet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetModel> searchPet(@PathVariable Integer id) {
        PetModel pet = petService.findyPetById(id);
        return ResponseEntity.status(200).body(pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Integer id) {
        petService.deletePetById(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PetModel> updatePet(@PathVariable Integer id, @RequestBody PetModel pet) {
        PetModel updatedPet = petService.updatePetById(id, pet);
        return ResponseEntity.status(200).body(updatedPet);
    }

}
