package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.implementations;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.OwnerEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.OwnerEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.OwnerJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OwnerJpaAdapter implements OwnerGateway {

    private final OwnerJpaRepository repository;
    private final PasswordEncoder passwordEncoder;

    public OwnerJpaAdapter(OwnerJpaRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Owner create(Owner owner) {
        OwnerEntity entity = OwnerEntityMapper.toEntity(owner);

        entity.setPassword(passwordEncoder.encode(owner.getPassword()));
        entity.setCreatedAt(LocalDateTime.now());
        entity.setLastUpdate(LocalDateTime.now());

        return OwnerEntityMapper.of(repository.save(entity)) ;
    }

    @Override
    public Owner updateOwnerById(Integer id, OwnerUpdateCommand command) {
        OwnerEntity existing  = repository.findById(id).orElse(null);

        existing.setPhoneNumber(command.phoneNumber());
        existing.setEmail(command.email());
        existing.setCep(command.cep());
        existing.setNeighborhood(command.neighborhood());
        existing.setStreet(command.street());
        existing.setNumber(command.number());
        existing.setComplement(command.complement());
        existing.setLastUpdate(LocalDateTime.now());

        return OwnerEntityMapper.of(repository.save(existing)) ;
    }

    @Override
    public Owner getOwnerByEmail(String email) {
        OwnerEntity entity = repository.findByEmail(email).orElse(null);

        if (entity != null)
            return OwnerEntityMapper.of(entity);

        throw new EntityNotFoundException("Não encontrado usuário com email: " +email);
    }

    @Override
    public Owner getOwnerById(Integer id) {
        OwnerEntity entity = repository.findById(id).orElse(null);
        if (entity == null)
            throw new EntityNotFoundException("Não encontrado usuário com id: " +id);

        return OwnerEntityMapper.of(entity);
    }

    @Override
    public void deleteOwnerById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
}