package br.com.petcolumbia.api_pet_columbia.Services;

import br.com.petcolumbia.api_pet_columbia.Models.UserModel;
import br.com.petcolumbia.api_pet_columbia.Repositories.IUserRepository;
import br.com.petcolumbia.api_pet_columbia.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository repository;

    public UserModel createUser(UserModel newUser){
        return repository.save(newUser);
    }

    public UserModel findyUserById(@PathVariable Integer id) {
        Optional<UserModel> byId = repository.findById(id);

        if(byId.isEmpty())
            throw new EntityNotFoundException();

        return byId.get();
    }

    public void deleteUserById(Integer id) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        repository.deleteById(id);
    }

    public UserModel updateUserById(Integer id, UserModel user) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        user.setId(id);
        return repository.save(user);
    }
}
