package br.com.petcolumbia.api_pet_columbia.Repositories.Interfaces;

import br.com.petcolumbia.api_pet_columbia.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository <UserModel, Integer> {
}
