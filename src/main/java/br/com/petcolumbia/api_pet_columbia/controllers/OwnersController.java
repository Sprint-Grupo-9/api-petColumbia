package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerUpdatePasswordDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerDetailResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerResponseDto;
import br.com.petcolumbia.api_pet_columbia.services.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
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

   @Operation(summary = "Regista um novo usuário, Recebe um dto de criação de usuário")
   @PostMapping
   public ResponseEntity<OwnerResponseDto> registerOwner(@Valid @RequestBody OwnerCreateDto newOwner){
      OwnerResponseDto savedOwner = ownerService.createOwner(newOwner);
      return ResponseEntity.status(201).body(savedOwner);
   }

   @Operation(summary = "Procura um usuário pelo id", description = "retorna dados do usuário, com exceção da senha, para tela de atualização de usuário")
   @GetMapping("/{id}")
   public ResponseEntity<OwnerDetailResponseDto> searchOwner(@PathVariable Integer id) {
      OwnerDetailResponseDto owner = ownerService.getOwnerDetailById(id);
      return ResponseEntity.status(200).body(owner);
   }

   @Operation(summary = "Deleta um usuário pelo id")
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteOwner(@PathVariable Integer id) {
      ownerService.deleteOwnerById(id);
      return ResponseEntity.status(204).build();
   }

   @Operation(summary = "Atualiza um usuário pelo id e objeto", description = "recebe o id e objeto para atualizar, com exceção da senha")
   @PutMapping("/{id}")
   public ResponseEntity<OwnerResponseDto> updateOwner(@PathVariable Integer id, @Valid @RequestBody OwnerUpdateDto ownerDto) {
      OwnerResponseDto owner = ownerService.updateOwnerById(id, ownerDto);
      return ResponseEntity.status(200).body(owner);
   }

   @Operation(summary = "Atualiza a senha de um usuário pelo id e objeto", description = "recebe o id, senha atual e senha nova")
   @PatchMapping("/{id}")
   public ResponseEntity<OwnerResponseDto> updateOwnerPassword(@PathVariable Integer id, @Valid @RequestBody OwnerUpdatePasswordDto ownerDto) {
      OwnerResponseDto owner = ownerService.updatePasswordById(id, ownerDto);
      return ResponseEntity.status(200).body(owner);
   }

   @Operation(summary = "Realiza o login do usuário, recebe o email e senha")
   @PostMapping("/login")
   public ResponseEntity<OwnerResponseDto> login(@Valid @RequestBody OwnerLoginDto ownerLogin) {
      OwnerResponseDto authenticatedUser = ownerService.login(ownerLogin);
      return ResponseEntity.status(200).body(authenticatedUser);
   }
}
