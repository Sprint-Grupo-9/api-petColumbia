package br.com.petcolumbia.api_pet_columbia.Repositories;

import br.com.petcolumbia.api_pet_columbia.Models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<AddressModel, Integer> {
}
