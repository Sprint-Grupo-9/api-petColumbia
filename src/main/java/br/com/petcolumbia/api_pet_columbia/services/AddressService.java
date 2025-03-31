package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AddressModel;
import br.com.petcolumbia.api_pet_columbia.repositories.IAddressRepository;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private IAddressRepository addressRepository;

    public AddressModel createAddress(AddressModel newaddress){
        return addressRepository.save(newaddress);
    }

    public AddressModel findyAddressById(@PathVariable Integer id) {
        Optional<AddressModel> byId = addressRepository.findById(id);

        if(byId.isEmpty())
            throw new EntityNotFoundException();

        return byId.get();
    }

    public void deleteAddressById(Integer id) {
        if(!addressRepository.existsById(id))
            throw new EntityNotFoundException();

        addressRepository.deleteById(id);
    }

    public AddressModel updateAddressById(Integer id, AddressModel address) {
        if(!addressRepository.existsById(id))
            throw new EntityNotFoundException();

        address.setId(id);
        return addressRepository.save(address);
    }
}
