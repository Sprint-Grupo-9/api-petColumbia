package br.com.petcolumbia.api_pet_columbia.infrastructure.web;

import br.com.petcolumbia.api_pet_columbia.core.adapter.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.command_mapper.OwnerCommandMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper.OwnerEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.response_mapper.OwnerResponseMapper;
import br.com.petcolumbia.api_pet_columbia.old.dtos.requests.ownerDtos.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.ownerDtos.OwnerInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.ownerDtos.OwnerResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerGateway ownerGateway;

    public OwnerController(OwnerGateway ownerGateway) {
        this.ownerGateway = ownerGateway;
    }

    @PostMapping
    @Operation(summary = "Regista um novo usuário, Recebe um dto de criação de usuário")
    public ResponseEntity<OwnerResponseDto> registerOwner(@Valid @RequestBody OwnerCreateDto newOwner){
        OwnerCreateCommand command = OwnerCommandMapper.of(newOwner);

        Owner createdOwner = ownerGateway.create(OwnerCommandMapper.of(command));

        return ResponseEntity.status(201).body(OwnerResponseMapper.of(createdOwner));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Procura um usuário pelo id", description = "retorna dados do usuário, com exceção da senha, para tela de atualização de usuário")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<OwnerInfoResponseDto> searchOwner(@PathVariable Integer id) {
        Owner owner = ownerGateway.getOwnerById(id);
        return ResponseEntity.status(200).body(OwnerEntityMapper.to(owner));
    }
}
