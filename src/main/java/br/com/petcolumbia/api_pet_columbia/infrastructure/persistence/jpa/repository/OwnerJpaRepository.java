package br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.jpa.repository;

import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.OwnerEntity;
import br.com.petcolumbia.api_pet_columbia.old.domain.entities.OwnerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerJpaRepository extends JpaRepository<OwnerEntity, Integer> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPhoneNumber(String telefone);

    boolean existsByEmailAndIdNot(String email, Integer id);
    boolean existsByCpfAndIdNot(String cpf, Integer id);
    boolean existsByPhoneNumberAndIdNot(String telefone, Integer id);

    Optional<OwnerModel> findByEmail(String email);
}
