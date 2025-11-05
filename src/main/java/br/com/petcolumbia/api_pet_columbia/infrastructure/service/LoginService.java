package br.com.petcolumbia.api_pet_columbia.infrastructure.service;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.owner.OwnerTokenResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.OwnerEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper.OwnerResponseMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.owner.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.OwnerEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.OwnerJpaRepository;
import br.com.petcolumbia.api_pet_columbia.infrastructure.security.JwtTokenManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final OwnerJpaRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenManager tokenManager;

    public LoginService(OwnerJpaRepository repository, AuthenticationManager authenticationManager, JwtTokenManager tokenManager) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    public OwnerTokenResponseDto authenticateOwner(OwnerLoginDto ownerLogin){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                ownerLogin.getEmail(), ownerLogin.getPassword()
        );

        final Authentication authentication = authenticationManager.authenticate(credentials);

        OwnerEntity authenticateOwner = repository.findByEmail(ownerLogin.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Email ou senha inválidos"));

        Owner owner = OwnerEntityMapper.of(authenticateOwner);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = tokenManager.generateToken(authentication);

        return OwnerResponseMapper.toTokenResponse(owner, token);
    }
}
