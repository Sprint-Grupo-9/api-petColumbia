package br.com.petcolumbia.api_pet_columbia.infrastructure.security;

import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.OwnerEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper.OwnerResponseMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.OwnerEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.OwnerJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final OwnerJpaRepository repository;

    public AuthenticationService(OwnerJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OwnerEntity entity = repository.findByEmail(username).orElse(null);

        if (entity == null)
            throw new EntityNotFoundException("Usuário não encontrado com o email: " + username);

        Owner owner = OwnerEntityMapper.of(entity);

        var a = OwnerResponseMapper.toDetailsDto(owner);
        return a;
    }
}
