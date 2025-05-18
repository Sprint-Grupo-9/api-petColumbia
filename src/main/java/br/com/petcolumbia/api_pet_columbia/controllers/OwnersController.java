package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.OwnerMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.ownerDtos.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.ownerDtos.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.ownerDtos.OwnerUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.ownerDtos.OwnerUpdatePasswordDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.ownerDtos.OwnerInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.ownerDtos.OwnerResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.ownerDtos.OwnerTokenResponseDto;
import br.com.petcolumbia.api_pet_columbia.services.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class OwnersController {

    private final OwnerService ownerService;

   public OwnersController(OwnerService ownerService) {
      this.ownerService = ownerService;
   }

   @PostMapping
   @Operation(summary = "Regista um novo usuário, Recebe um dto de criação de usuário")
   public ResponseEntity<OwnerTokenResponseDto> registerOwner(@Valid @RequestBody OwnerCreateDto newOwner){
      return ResponseEntity.status(201).body(ownerService.createOwner(newOwner));
   }

   @GetMapping("/{id}")
   @Operation(summary = "Procura um usuário pelo id", description = "retorna dados do usuário, com exceção da senha, para tela de atualização de usuário")
   @SecurityRequirement(name = "Bearer")
   public ResponseEntity<OwnerInfoResponseDto> searchOwner(@PathVariable Integer id) {
      OwnerModel owner = ownerService.getOwnerDetailById(id);
      return ResponseEntity.status(200).body(OwnerMapper.entityToDetailResponseDto(owner));
   }

   @DeleteMapping("/{id}")
   @Operation(summary = "Deleta um usuário pelo id")
   @SecurityRequirement(name = "Bearer")
   public ResponseEntity<Void> deleteOwner(@PathVariable Integer id) {
      ownerService.deleteOwnerById(id);
      return ResponseEntity.status(204).build();
   }

   @PutMapping("/{id}")
   @Operation(summary = "Atualiza um usuário pelo id e objeto", description = "recebe o id e objeto para atualizar, com exceção da senha")
   @SecurityRequirement(name = "Bearer")
   public ResponseEntity<OwnerResponseDto> updateOwner(@PathVariable Integer id, @Valid @RequestBody OwnerUpdateDto owner) {
      OwnerModel updatedOwner = ownerService.updateOwnerById(id, OwnerMapper.updateDtoToEntity(owner));
      return ResponseEntity.status(200).body(OwnerMapper.entityToResponseDto(updatedOwner));
   }

   @Operation(summary = "Atualiza a senha de um usuário pelo id e objeto", description = "recebe o id, senha atual e senha nova")
   @PatchMapping("/{id}")
   @SecurityRequirement(name = "Bearer")
   public ResponseEntity<OwnerResponseDto> updateOwnerPassword(@PathVariable Integer id, @Valid @RequestBody OwnerUpdatePasswordDto owner) {
      OwnerModel updatedOwner = ownerService.updatePasswordById(id, owner);
      return ResponseEntity.status(200).body(OwnerMapper.entityToResponseDto(updatedOwner));
   }

   @Operation(summary = "Realiza o autenticação do usuário, recebe o email e senha")
   @PostMapping("/login")
   public ResponseEntity<OwnerTokenResponseDto> login(@Valid @RequestBody OwnerLoginDto ownerLogin) {
      OwnerTokenResponseDto authenticatedUser = ownerService.authenticateOwner(ownerLogin);
      return ResponseEntity.status(200).body(authenticatedUser);
   }
}
