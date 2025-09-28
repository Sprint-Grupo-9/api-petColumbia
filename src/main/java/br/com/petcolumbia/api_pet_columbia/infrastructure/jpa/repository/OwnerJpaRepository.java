package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository;

import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.OwnerEntity;
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

    boolean existsById(Integer id);

    Optional<OwnerEntity> findByEmail(String email);
}
