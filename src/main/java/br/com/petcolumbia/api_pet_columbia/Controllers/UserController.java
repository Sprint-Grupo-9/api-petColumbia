package br.com.petcolumbia.api_pet_columbia.Controllers;

import br.com.petcolumbia.api_pet_columbia.Models.UserModel;
import br.com.petcolumbia.api_pet_columbia.Repositories.Interfaces.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
   private IUserRepository repository;

   @PostMapping
   public ResponseEntity<UserModel>register(@RequestBody UserModel newUser){
      UserModel savedUser = repository.save(newUser);
      return ResponseEntity.status(201).body(savedUser);
   }

   @GetMapping("/{id}")
   public ResponseEntity<UserModel> searchUserById(@PathVariable Integer id) {
      if (id == null || id <= 0) {
         return ResponseEntity.status(400).build();
      }

      Optional<UserModel> user = repository.findById(id);
      if (user.isPresent()) {
         return ResponseEntity.status(200).body(user.get());
      }

      return ResponseEntity.status(404).build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
      if (id == null || id < 1) {
         return ResponseEntity.status(400).build();
      }
      if (!repository.existsById(id)) {
         return ResponseEntity.status(404).build();
      }
      repository.deleteById(id);
      return ResponseEntity.status(204).build();
   }

   @PutMapping("/{id}")
   @Transactional
   public ResponseEntity<UserModel> updateUser(@PathVariable Integer id, @RequestBody UserModel newUser) {
      if (id == null || id < 1) {
         return ResponseEntity.status(400).build();
      }

      if (!repository.existsById(id)) {
         return ResponseEntity.status(404).build();
      }

      newUser.setId(id);
      UserModel updatedUser = repository.save(newUser);

      return ResponseEntity.status(204).body(updatedUser);
   }
}
