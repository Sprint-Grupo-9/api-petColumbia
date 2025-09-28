package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.implementations;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerValidationGateway;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.OwnerJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerValidationJpaAdapter implements OwnerValidationGateway {

    private final OwnerJpaRepository repository;

    public OwnerValidationJpaAdapter(OwnerJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public boolean existsByPhoneNumber(String telefone) {
        return repository.existsByPhoneNumber(telefone);
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, Integer id) {
        return repository.existsByEmailAndIdNot(email, id);
    }

    @Override
    public boolean existsByCpfAndIdNot(String cpf, Integer id) {
        return repository.existsByCpfAndIdNot(cpf, id);
    }

    @Override
    public boolean existsByPhoneNumberAndIdNot(String telefone, Integer id) {
        return repository.existsByPhoneNumberAndIdNot(telefone, id);
    }
}
