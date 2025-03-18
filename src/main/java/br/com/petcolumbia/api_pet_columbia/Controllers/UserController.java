package br.com.petcolumbia.api_pet_columbia.Controllers;

import br.com.petcolumbia.api_pet_columbia.Models.UserModel;
import br.com.petcolumbia.api_pet_columbia.Repositories.IUserRepository;
import br.com.petcolumbia.api_pet_columbia.Services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

   @PostMapping
   public ResponseEntity<UserModel>registerUser(@RequestBody UserModel newUser){
      UserModel savedUser = userService.createUser(newUser);
      return ResponseEntity.status(201).body(savedUser);
   }

   @GetMapping("/{id}")
   public ResponseEntity<UserModel> searchUser(@PathVariable Integer id) {
      UserModel user = userService.findyUserById(id);
      return ResponseEntity.status(200).body(user);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
      userService.deleteUserById(id);
      return ResponseEntity.status(204).build();
   }

   @PutMapping("/{id}")
   @Transactional
   public ResponseEntity<UserModel> updateUser(@PathVariable Integer id, @RequestBody UserModel user) {
      UserModel updatedUser = userService.updateUserById(id, user);
      return ResponseEntity.status(200).body(updatedUser);
   }
}
