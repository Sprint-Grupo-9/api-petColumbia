package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<AddressModel, Integer> {
}
