package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerUpdatePasswordDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerTokenResponseDto;
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
   public ResponseEntity<OwnerResponseDto> registerOwner(@Valid @RequestBody OwnerCreateDto newOwner){
      OwnerResponseDto savedOwner = ownerService.createOwner(newOwner);
      return ResponseEntity.status(201).body(savedOwner);
   }

   @GetMapping("/{id}")
   @Operation(summary = "Procura um usuário pelo id", description = "retorna dados do usuário, com exceção da senha, para tela de atualização de usuário")
   @SecurityRequirement(name = "Bearer")
   public ResponseEntity<OwnerInfoResponseDto> searchOwner(@PathVariable Integer id) {
      OwnerInfoResponseDto owner = ownerService.getOwnerDetailById(id);
      return ResponseEntity.status(200).body(owner);
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
      OwnerResponseDto updatedOwner = ownerService.updateOwnerById(id, owner);
      return ResponseEntity.status(200).body(updatedOwner);
   }

   @Operation(summary = "Atualiza a senha de um usuário pelo id e objeto", description = "recebe o id, senha atual e senha nova")
   @PatchMapping("/{id}")
   @SecurityRequirement(name = "Bearer")
   public ResponseEntity<OwnerResponseDto> updateOwnerPassword(@PathVariable Integer id, @Valid @RequestBody OwnerUpdatePasswordDto owner) {
      OwnerResponseDto updatedOwner = ownerService.updatePasswordById(id, owner);
      return ResponseEntity.status(200).body(updatedOwner);
   }

   @Operation(summary = "Realiza o autenticação do usuário, recebe o email e senha")
   @PostMapping("/login")
   public ResponseEntity<OwnerTokenResponseDto> login(@Valid @RequestBody OwnerLoginDto ownerLogin) {
      OwnerTokenResponseDto authenticatedUser = ownerService.authenticateOwner(ownerLogin);
      return ResponseEntity.status(200).body(authenticatedUser);
   }
}
