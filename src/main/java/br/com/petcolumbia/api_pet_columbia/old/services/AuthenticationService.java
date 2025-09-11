package br.com.petcolumbia.api_pet_columbia.old.services;

import br.com.petcolumbia.api_pet_columbia.old.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.ownerDtos.OwnerDetailsDto;
import br.com.petcolumbia.api_pet_columbia.old.exceptions.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.old.repositories.IOwnerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    private final IOwnerRepository ownerRepository;

    public AuthenticationService(IOwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<OwnerModel> owner = ownerRepository.findByEmail(username);

        if (owner.isEmpty())
            throw new EntityNotFoundException("Usuário não encontrado: " + username);

        return new OwnerDetailsDto(owner.get());
    }
}
