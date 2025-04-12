package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerCreateUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerLoginDto;
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
   public ResponseEntity<OwnerResponseDto> registerOwner(@Valid @RequestBody OwnerCreateUpdateDto newOwner){
      OwnerResponseDto savedOwner = ownerService.createOwner(newOwner);
      return ResponseEntity.status(201).body(savedOwner);
   }

   @Operation(summary = "Deleta um usuário pelo id")
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteOwner(@PathVariable Integer id) {
      ownerService.deleteOwnerById(id);
      return ResponseEntity.status(204).build();
   }

   @Operation(summary = "Atualiza um usuário pelo id, recebe o id e o objeto")
   @PutMapping("/{id}")
   public ResponseEntity<OwnerResponseDto> updateOwner(@PathVariable Integer id, @Valid @RequestBody OwnerCreateUpdateDto owner) {
      OwnerResponseDto updatedOwner = ownerService.updateOwnerById(id, owner);
      return ResponseEntity.status(200).body(updatedOwner);
   }

   @Operation(summary = "Realiza o login do usuário, recebe o email e senha")
   @PostMapping("/login")
   public ResponseEntity<OwnerResponseDto> login(@Valid @RequestBody OwnerLoginDto ownerLogin) {
      OwnerResponseDto authenticatedUser = ownerService.login(ownerLogin);
      return ResponseEntity.status(200).body(authenticatedUser);
   }
}
