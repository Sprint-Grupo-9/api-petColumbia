package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.config.security.JwtTokenManager;
import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.OwnerMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.ownerDtos.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.ownerDtos.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.ownerDtos.OwnerUpdatePasswordDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.ownerDtos.OwnerTokenResponseDto;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityConflictException;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityUnauthorizedException;
import br.com.petcolumbia.api_pet_columbia.repositories.IOwnerRepository;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OwnerService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenManager tokenManager;
    private final AuthenticationManager authenticationManager;
    private final IOwnerRepository ownerRepository;

    public OwnerService(PasswordEncoder passwordEncoder, JwtTokenManager tokenManager, AuthenticationManager authenticationManager, IOwnerRepository ownerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
        this.ownerRepository = ownerRepository;
    }

    public OwnerTokenResponseDto createOwner(OwnerCreateDto newOwner) {
        if (isDuplicateFields(newOwner.getEmail(), newOwner.getCpf(), newOwner.getPhoneNumber(), null))
            throw new EntityConflictException("Já existe um usuário com o e-mail, CPF ou telefone informados.");

        OwnerModel owner = OwnerMapper.createDtoToEntity(newOwner);
        owner.setAdm(false);
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        ownerRepository.save(owner);

        final Authentication authentication = new UsernamePasswordAuthenticationToken(owner.getEmail(), newOwner.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = tokenManager.generateToken(authentication);

        return OwnerMapper.of(owner, token);
    }


    public OwnerModel getOwnerDetailById(Integer id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado pelo id: " + id));
    }

    public OwnerModel getOwnerById(Integer id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado pelo id: " + id));
    }

    public void deleteOwnerById(Integer id) {
        if(!ownerRepository.existsById(id))
            throw new EntityNotFoundException("Não encontrado usuário com id:" + id);

        ownerRepository.deleteById(id);
    }

    public OwnerModel updateOwnerById(Integer id, OwnerModel updatedOwner) {
        OwnerModel owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (isDuplicateFields(updatedOwner.getEmail(), updatedOwner.getCpf(), updatedOwner.getPhoneNumber(), id))
            throw new EntityConflictException("Já existe um usuário com o e-mail, CPF ou telefone informados.");

        owner.setName(owner.getName());
        owner.setEmail(updatedOwner.getEmail());
        owner.setCpf(owner.getCpf());
        owner.setPhoneNumber(updatedOwner.getPhoneNumber());
        owner.setCep(updatedOwner.getCep());
        owner.setNeighborhood(updatedOwner.getNeighborhood());
        owner.setStreet(updatedOwner.getStreet());
        owner.setNumber(updatedOwner.getNumber());
        owner.setComplement(updatedOwner.getComplement());
        owner.setAdm(owner.getAdm());

        ownerRepository.save(owner);

        return owner;
    }

    public OwnerModel updatePasswordById(Integer id, OwnerUpdatePasswordDto dto){
        OwnerModel owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.getCurrentPassword(), owner.getPassword()))
            throw new EntityUnauthorizedException("Senha atual incorreta");

        owner.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        owner.setLastUpdate(LocalDateTime.now());

        return owner;
    }

    public OwnerTokenResponseDto authenticateOwner(OwnerLoginDto ownerLogin){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                ownerLogin.getEmail(), ownerLogin.getPassword()
        );

        final Authentication authentication = authenticationManager.authenticate(credentials);

        OwnerModel authenticateOwner = ownerRepository.findByEmail(ownerLogin.getEmail())
                .orElseThrow(() -> new EntityUnauthorizedException("Email ou senha inválidos"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = tokenManager.generateToken(authentication);

        return OwnerMapper.of(authenticateOwner, token);
    }

    public boolean isDuplicateFields(String email, String cpf, String phoneNumber, Integer id) {
        if (id == null)  //Create
            return ownerRepository.existsByEmail(email)
                    || ownerRepository.existsByCpf(cpf)
                    || ownerRepository.existsByPhoneNumber(phoneNumber);

        //Update
        return ownerRepository.existsByEmailAndIdNot(email, id)
                    || ownerRepository.existsByCpfAndIdNot(cpf, id)
                    || ownerRepository.existsByPhoneNumberAndIdNot(phoneNumber, id);
    }


}