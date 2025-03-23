package br.com.petcolumbia.api_pet_columbia.Repositories;

import br.com.petcolumbia.api_pet_columbia.Models.OwnerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository <OwnerModel, Integer> {
}
