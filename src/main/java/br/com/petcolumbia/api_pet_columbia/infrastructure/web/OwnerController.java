package br.com.petcolumbia.api_pet_columbia.infrastructure.web;

import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerCreateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdatePasswordCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.owner.OwnerInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.owner.OwnerResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.owner.OwnerTokenResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.owner.*;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.command_mapper.OwnerCommandMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper.OwnerResponseMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.owner.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.owner.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.owner.OwnerUpdateDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.owner.OwnerUpdatePasswordDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final CreateOwnerUseCase createOwnerUseCase;
    private final UpdateOwnerPasswordByIdUseCase updateOwnerPasswordByIdUseCase;
    private final OwnerByEmailUseCase ownerByEmailUseCase;
    private final UpdateOwnerUseCase updateOwnerUseCase;
    private final OwnerByIdUseCase ownerByIdUseCase;
    private final LoginService loginService;

    public OwnerController(
            CreateOwnerUseCase createOwnerUseCase,
            UpdateOwnerPasswordByIdUseCase updateOwnerPasswordByIdUseCase,
            OwnerByEmailUseCase ownerByEmailUseCase,
            UpdateOwnerUseCase updateOwnerUseCase,
            OwnerByIdUseCase ownerByIdUseCase,
            LoginService loginService
    ) {
        this.createOwnerUseCase = createOwnerUseCase;
        this.updateOwnerPasswordByIdUseCase = updateOwnerPasswordByIdUseCase;
        this.ownerByEmailUseCase = ownerByEmailUseCase;
        this.updateOwnerUseCase = updateOwnerUseCase;
        this.ownerByIdUseCase = ownerByIdUseCase;
        this.loginService = loginService;
    }

    @PostMapping
    @Operation(summary = "Regista um novo usuário, Recebe um dto de criação de usuário")
    public ResponseEntity<OwnerResponseDto> registerOwner(@Valid @RequestBody OwnerCreateDto newOwner){
        OwnerCreateCommand command = OwnerCommandMapper.of(newOwner);
        Owner owner = createOwnerUseCase.execute(command);
        return ResponseEntity.status(201).body(OwnerResponseMapper.toResponse(owner));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Procura um usuário pelo id", description = "retorna dados do usuário, com exceção da senha, para tela de atualização de usuário")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<OwnerInfoResponseDto> searchOwner(@PathVariable Integer id) {
        Owner owner = ownerByIdUseCase.execute(id);
        OwnerInfoResponseDto response = OwnerResponseMapper.toInfoResponse(owner);
        return ResponseEntity.status(200).body(response);
    }

    @Deprecated() // Not used
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário pelo id (não utilizado)")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deleteOwner(@PathVariable Integer id) {
        throw new UnsupportedOperationException("Delete operation is deprecated and not implemented via use case.");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário pelo id e objeto", description = "recebe o id e objeto para atualizar, com exceção da senha")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<OwnerResponseDto> updateOwner(@PathVariable Integer id, @Valid @RequestBody OwnerUpdateDto dto) {
        OwnerUpdateCommand command = OwnerCommandMapper.of(dto);
        Owner updatedOwner = updateOwnerUseCase.execute(id, command);
        return ResponseEntity.status(200).body(OwnerResponseMapper.toResponse(updatedOwner));
    }

    @Operation(summary = "Atualiza a senha de um usuário pelo id e objeto", description = "recebe o id, senha atual e senha nova")
    @PatchMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<OwnerResponseDto> updateOwnerPassword(@PathVariable Integer id, @Valid @RequestBody OwnerUpdatePasswordDto dto) {
        OwnerUpdatePasswordCommand command = OwnerCommandMapper.of(dto);
        Owner owner = updateOwnerPasswordByIdUseCase.execute(id, command);
        return ResponseEntity.status(200).body(OwnerResponseMapper.toResponse(owner));
    }

    @Operation(summary = "Realiza o autenticação do usuário, recebe o email e senha")
    @PostMapping("/login")
    public ResponseEntity<OwnerTokenResponseDto> login(@Valid @RequestBody OwnerLoginDto dto) {
        OwnerTokenResponseDto authenticatedUser = loginService.authenticateOwner(dto);
        return ResponseEntity.status(200).body(authenticatedUser);
    }
}
