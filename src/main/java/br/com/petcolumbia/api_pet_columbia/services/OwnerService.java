package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.models.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.repositories.IUserRepository;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private IUserRepository repository;

    public OwnerModel createUser(OwnerModel newUser){
        return repository.save(newUser);
    }

    public OwnerModel findyUserById(@PathVariable Integer id) {
        Optional<OwnerModel> byId = repository.findById(id);

        if(byId.isEmpty())
            throw new EntityNotFoundException();

        return byId.get();
    }

    public void deleteUserById(Integer id) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        repository.deleteById(id);
    }

    public OwnerModel updateUserById(Integer id, OwnerModel user) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        user.setId(id);
        return repository.save(user);
    }
}
