package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.PetCreateUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerDetailResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.PetResponseDto;
import br.com.petcolumbia.api_pet_columbia.services.OwnerService;
import br.com.petcolumbia.api_pet_columbia.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
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

    @Operation(summary = "Cria um novo pet, recebe o id do usuário e uma dto de criação de pet")
    @PostMapping
    public ResponseEntity<PetResponseDto>registerPet(@PathVariable Integer ownerId, @Valid @RequestBody PetCreateUpdateDto dto){
        PetResponseDto savedPet = petService.createPet(ownerId, dto);
        return ResponseEntity.status(201).body(savedPet);
    }

    @Operation(summary = "Busca um pet pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDto> searchPet(@PathVariable Integer id) {
        PetResponseDto pet = petService.findPetById(id);
        return ResponseEntity.status(200).body(pet);
    }

    @Operation(summary = "Buscar todos os pets de um usuário", description = "recebe o id do usuário")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<PetResponseDto>> getAllPetsByOwnerId(@PathVariable Integer ownerId) {
        List<PetResponseDto> allPets = petService.listAllPetsByOwner(ownerId);
        if(allPets.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(allPets);
    }

    @Operation(summary = "Deleta um pet pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Integer id) {
        petService.deletePetById(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Atualiza um pet pelo id e objeto", description = "recebe o id e o objeto para atualizar")
    @PutMapping("/{id}")
    public ResponseEntity<PetResponseDto> updatePet(@PathVariable Integer id, @RequestBody PetCreateUpdateDto pet) {
        PetResponseDto updatedPet = petService.updatePetById(id, pet);
        return ResponseEntity.status(200).body(updatedPet);
    }

}
