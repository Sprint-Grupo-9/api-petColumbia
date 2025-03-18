package br.com.petcolumbia.api_pet_columbia.Controllers;

import br.com.petcolumbia.api_pet_columbia.Models.PetModel;
import br.com.petcolumbia.api_pet_columbia.Models.PetModel;
import br.com.petcolumbia.api_pet_columbia.Repositories.IPetRepository;
import br.com.petcolumbia.api_pet_columbia.Services.PetService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    private PetService petService;

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
