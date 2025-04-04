package br.com.petcolumbia.api_pet_columbia.Controllers;

import br.com.petcolumbia.api_pet_columbia.Models.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.Services.OwnerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class OwnersController {

    @Autowired
    private OwnerService ownerService;

   @PostMapping
   public ResponseEntity<OwnerModel>registerUser(@RequestBody OwnerModel newUser){
      OwnerModel savedUser = ownerService.createUser(newUser);
      return ResponseEntity.status(201).body(savedUser);
   }

   @GetMapping("/{id}")
   public ResponseEntity<OwnerModel> searchUser(@PathVariable Integer id) {
      OwnerModel user = ownerService.findyUserById(id);
      return ResponseEntity.status(200).body(user);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
      ownerService.deleteUserById(id);
      return ResponseEntity.status(204).build();
   }

   @PutMapping("/{id}")
   @Transactional
   public ResponseEntity<OwnerModel> updateUser(@PathVariable Integer id, @RequestBody OwnerModel user) {
      OwnerModel updatedUser = ownerService.updateUserById(id, user);
      return ResponseEntity.status(200).body(updatedUser);
   }
}
