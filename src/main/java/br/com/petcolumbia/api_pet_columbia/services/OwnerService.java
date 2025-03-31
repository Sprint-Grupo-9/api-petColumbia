package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.repositories.IOwnerRepository;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private IOwnerRepository ownerRepository;

    public OwnerModel createUser(OwnerModel newUser){
        return ownerRepository.save(newUser);
    }

    public OwnerModel findyUserById(@PathVariable Integer id) {
        Optional<OwnerModel> byId = ownerRepository.findById(id);

        if(byId.isEmpty())
            throw new EntityNotFoundException();

        return byId.get();
    }

    public void deleteUserById(Integer id) {
        if(!ownerRepository.existsById(id))
            throw new EntityNotFoundException();

        ownerRepository.deleteById(id);
    }

    public OwnerModel updateUserById(Integer id, OwnerModel user) {
        if(!ownerRepository.existsById(id))
            throw new EntityNotFoundException();

        user.setId(id);
        return ownerRepository.save(user);
    }
}
