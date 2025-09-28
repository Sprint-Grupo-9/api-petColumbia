package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.implementations;

import br.com.petcolumbia.api_pet_columbia.core.adapter.owner.OwnerCredentialsGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdatePasswordCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityUnauthorizedException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.OwnerEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.OwnerEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.OwnerJpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OwnerCredentialsJpaAdapter implements OwnerCredentialsGateway {

    private final OwnerJpaRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public OwnerCredentialsJpaAdapter(OwnerJpaRepository repository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Owner updatePasswordById(Integer id, OwnerUpdatePasswordCommand command) {
        OwnerEntity entity = repository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }

        if (!passwordEncoder.matches(command.currentPassword(), entity.getPassword())) {
            throw new EntityUnauthorizedException("Senha atual incorreta");
        }

        entity.setPassword(passwordEncoder.encode(command.newPassword()));
        return OwnerEntityMapper.of(repository.save(entity));
    }
}
