package br.com.petcolumbia.api_pet_columbia.Services;

import br.com.petcolumbia.api_pet_columbia.Models.Owner;
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

    public Owner createUser(Owner newUser){
        return repository.save(newUser);
    }

    public Owner findyUserById(@PathVariable Integer id) {
        Optional<Owner> byId = repository.findById(id);

        if(byId.isEmpty())
            throw new EntityNotFoundException();

        return byId.get();
    }

    public void deleteUserById(Integer id) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        repository.deleteById(id);
    }

    public Owner updateUserById(Integer id, Owner user) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        user.setId(id);
        return repository.save(user);
    }
}
