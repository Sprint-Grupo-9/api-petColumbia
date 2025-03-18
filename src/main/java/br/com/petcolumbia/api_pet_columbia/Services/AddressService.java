package br.com.petcolumbia.api_pet_columbia.Services;

import br.com.petcolumbia.api_pet_columbia.Models.AddressModel;
import br.com.petcolumbia.api_pet_columbia.Repositories.IAddressRepository;
import br.com.petcolumbia.api_pet_columbia.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private IAddressRepository repository;

    public AddressModel createAddress(AddressModel newaddress){
        return repository.save(newaddress);
    }

    public AddressModel findyAddressById(@PathVariable Integer id) {
        Optional<AddressModel> byId = repository.findById(id);

        if(byId.isEmpty())
            throw new EntityNotFoundException();

        return byId.get();
    }

    public void deleteAddressById(Integer id) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        repository.deleteById(id);
    }

    public AddressModel updateAddressById(Integer id, AddressModel address) {
        if(!repository.existsById(id))
            throw new EntityNotFoundException();

        address.setId(id);
        return repository.save(address);
    }
}
