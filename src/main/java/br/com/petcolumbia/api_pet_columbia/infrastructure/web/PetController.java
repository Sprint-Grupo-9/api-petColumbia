package br.com.petcolumbia.api_pet_columbia.infrastructure.web;

import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet.PetResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet.*;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.command_mapper.PetCommandMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper.PetResponseMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.pet.PetCreateDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.pet.PetUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final CreatePetUseCase createPetUseCase;
    private final FindPetByIdUseCase findPetByIdUseCase;
    private final ListAllPetsByOwnerUseCase listAllPetsByOwnerUseCase;
    private final UpdatePetByIdUseCase updatePetByIdUseCase;

    public PetController(
            CreatePetUseCase createPetUseCase,
            FindPetByIdUseCase findPetByIdUseCase,
            ListAllPetsByOwnerUseCase listAllPetsByOwnerUseCase,
            UpdatePetByIdUseCase updatePetByIdUseCase
    ) {
        this.createPetUseCase = createPetUseCase;
        this.findPetByIdUseCase = findPetByIdUseCase;
        this.listAllPetsByOwnerUseCase = listAllPetsByOwnerUseCase;
        this.updatePetByIdUseCase = updatePetByIdUseCase;
    }

    @PostMapping("/{ownerId}")
    @Operation(summary = "Cria um novo pet, recebe o id do usuário e uma dto de criação de pet")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<PetResponseDto> registerPet(
            @PathVariable Integer ownerId,
            @Valid @RequestBody PetCreateDto dto
    ) {
        PetCreateCommand command = PetCommandMapper.of(dto);
        Pet createdPet = createPetUseCase.execute(ownerId, command);
        return ResponseEntity.status(201).body(PetResponseMapper.toResponse(createdPet));
    }

    @GetMapping("/{id}/{ownerId}")
    @Operation(summary = "Busca um pet pelo id")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<PetResponseDto> searchPet(
            @PathVariable Integer id,
            @PathVariable Integer ownerId
    ) {
        Pet pet = findPetByIdUseCase.execute(id, ownerId);
        return ResponseEntity.status(200).body(PetResponseMapper.toResponse(pet));
    }

    @GetMapping("/all/{ownerId}")
    @Operation(summary = "Buscar todos os pets de um usuário", description = "recebe o id do usuário")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<PetResponseDto>> getAllPetsByOwnerId(@PathVariable Integer ownerId) {
        List<Pet> allPets = listAllPetsByOwnerUseCase.execute(ownerId);

        if (allPets.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<PetResponseDto> responsePets = PetResponseMapper.toResponseList(allPets);
        return ResponseEntity.status(200).body(responsePets);
    }

    @Deprecated() // Not used
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um pet pelo id (não utilizado)")
    @SecurityRequirement(name = "Bearer")
    @SuppressWarnings("unused")
    public ResponseEntity<Void> deletePet(@PathVariable Integer id) {
        throw new UnsupportedOperationException("Delete operation is deprecated and not implemented.");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um pet pelo id e objeto", description = "recebe o id e o objeto para atualizar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<PetResponseDto> updatePet(
            @PathVariable Integer id,
            @Valid @RequestBody PetUpdateDto dto
    ) {
        PetUpdateCommand command = PetCommandMapper.of(dto);
        Pet updatedPet = updatePetByIdUseCase.execute(id, command);
        return ResponseEntity.status(200).body(PetResponseMapper.toResponse(updatedPet));
    }
}

