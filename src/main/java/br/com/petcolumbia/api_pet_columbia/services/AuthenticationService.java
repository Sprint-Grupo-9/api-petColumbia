package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerDetailsDto;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.repositories.IOwnerRepository;
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
