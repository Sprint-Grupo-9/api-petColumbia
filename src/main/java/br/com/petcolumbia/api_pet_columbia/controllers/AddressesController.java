package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.models.AddressModel;
import br.com.petcolumbia.api_pet_columbia.services.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressesController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressModel>registerAddress(@RequestBody AddressModel newAddress){
        AddressModel savedAddress = addressService.createAddress(newAddress);
        return ResponseEntity.status(201).body(savedAddress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressModel> searchAddress(@PathVariable Integer id) {
        AddressModel address = addressService.findyAddressById(id);
        return ResponseEntity.status(200).body(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AddressModel> updateAddress(
            @PathVariable Integer id, @RequestBody AddressModel address) {
        AddressModel updatedAddress = addressService.updateAddressById(id, address);
        return ResponseEntity.status(200).body(updatedAddress);
    }

}
