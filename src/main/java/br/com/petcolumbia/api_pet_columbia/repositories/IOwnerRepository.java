package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOwnerRepository extends JpaRepository <OwnerModel, Integer> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPhoneNumber(String telefone);

    boolean existsByEmailAndIdNot(String email, Integer id);
    boolean existsByCpfAndIdNot(String cpf, Integer id);
    boolean existsByPhoneNumberAndIdNot(String telefone, Integer id);

    Optional<OwnerModel> findByEmail(String email);

}
