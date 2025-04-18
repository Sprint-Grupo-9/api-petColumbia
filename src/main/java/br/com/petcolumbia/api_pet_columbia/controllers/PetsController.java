package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.dtos.requests.PetCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.PetUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.PetResponseDto;
import br.com.petcolumbia.api_pet_columbia.services.OwnerService;
import br.com.petcolumbia.api_pet_columbia.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetsController {

    private final PetService petService;
    private final OwnerService ownerService;

    public PetsController(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @PostMapping
    @Operation(summary = "Cria um novo pet, recebe o id do usuário e uma dto de criação de pet")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<PetResponseDto>registerPet(@PathVariable Integer ownerId, @Valid @RequestBody PetCreateDto pet){
        PetResponseDto savedPet = petService.createPet(ownerId, pet);
        return ResponseEntity.status(201).body(savedPet);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um pet pelo id")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<PetResponseDto> searchPet(@PathVariable Integer id) {
        PetResponseDto pet = petService.findPetById(id);
        return ResponseEntity.status(200).body(pet);
    }

    @GetMapping("/all/{id}")
    @Operation(summary = "Buscar todos os pets de um usuário", description = "recebe o id do usuário")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<PetResponseDto>> getAllPetsByOwnerId(@PathVariable Integer ownerId) {
        List<PetResponseDto> allPets = petService.listAllPetsByOwner(ownerId);
        if(allPets.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(allPets);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um pet pelo id")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletePet(@PathVariable Integer id) {
        petService.deletePetById(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um pet pelo id e objeto", description = "recebe o id e o objeto para atualizar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<PetResponseDto> updatePet(@PathVariable Integer id, @RequestBody PetUpdateDto pet) {
        PetResponseDto updatedPet = petService.updatePetById(id, pet);
        return ResponseEntity.status(200).body(updatedPet);
    }

}
