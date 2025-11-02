package br.com.petcolumbia.api_pet_columbia.infrastructure.web;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet_offering.PetOfferingResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet_offering.PetOfferingWithPriceResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet_offering.GetPetOfferingsPricesByPetUseCase;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.pet_offering.ListAllPetOfferingUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering.PetOffering;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper.PetOfferingResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet-offerings")
public class PetOfferingController {

    private final ListAllPetOfferingUseCase listAllPetOfferingUseCase;
    private final GetPetOfferingsPricesByPetUseCase getPetOfferingsPricesByPetUseCase;

    public PetOfferingController(
            ListAllPetOfferingUseCase listAllPetOfferingUseCase,
            GetPetOfferingsPricesByPetUseCase getPetOfferingsPricesByPetUseCase
    ) {
        this.listAllPetOfferingUseCase = listAllPetOfferingUseCase;
        this.getPetOfferingsPricesByPetUseCase = getPetOfferingsPricesByPetUseCase;
    }

    @GetMapping
    @Operation(summary = "Busca todos os procedimentos")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<PetOfferingResponseDto>> getAllPetOfferings() {
        List<PetOffering> petOfferings = listAllPetOfferingUseCase.execute();
        List<PetOfferingResponseDto> response = PetOfferingResponseMapper.toResponseList(petOfferings);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/price/{petId}")
    @Operation(summary = "Busca os preços de todos os procedimentos para um pet específico",
               description = "Recebe o id do pet e uma lista de ids dos procedimentos")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<PetOfferingWithPriceResponseDto>> getPetOfferingsPricesOfPet(
            @PathVariable Integer petId,
            @RequestParam List<Integer> petOfferingIds
    ) {
        List<PetOfferingWithPriceResponseDto> petOfferingsList =
                getPetOfferingsPricesByPetUseCase.execute(petId, petOfferingIds);

        if (petOfferingsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(petOfferingsList);
    }
}

