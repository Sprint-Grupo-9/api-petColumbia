package br.com.petcolumbia.api_pet_columbia.Controllers;

import br.com.petcolumbia.api_pet_columbia.Models.Owner;
import br.com.petcolumbia.api_pet_columbia.Services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

   @PostMapping
   public ResponseEntity<Owner>registerUser(@RequestBody Owner newUser){
      Owner savedUser = userService.createUser(newUser);
      return ResponseEntity.status(201).body(savedUser);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Owner> searchUser(@PathVariable Integer id) {
      Owner user = userService.findyUserById(id);
      return ResponseEntity.status(200).body(user);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
      userService.deleteUserById(id);
      return ResponseEntity.status(204).build();
   }

   @PutMapping("/{id}")
   @Transactional
   public ResponseEntity<Owner> updateUser(@PathVariable Integer id, @RequestBody Owner user) {
      Owner updatedUser = userService.updateUserById(id, user);
      return ResponseEntity.status(200).body(updatedUser);
   }
}
