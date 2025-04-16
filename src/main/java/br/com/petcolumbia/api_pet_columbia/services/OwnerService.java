package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.OwnerMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerUpdatePasswordDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerDetailResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerResponseDto;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityConflictException;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityUnauthorizedException;
import br.com.petcolumbia.api_pet_columbia.repositories.IOwnerRepository;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OwnerService {

    private final IOwnerRepository ownerRepository;

    public OwnerService(IOwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public OwnerResponseDto createOwner(OwnerCreateDto newOwner){
        if(isDuplicateFields(newOwner.getEmail(), newOwner.getCpf(), newOwner.getPhoneNumber(), null))
            throw new EntityConflictException("Já existe um usuário com o e-mail, CPF ou telefone informados.");

        OwnerModel owner = OwnerMapper.createDtoToEntity(newOwner);
        ownerRepository.save(owner);

        return OwnerMapper.entityToResponseDto(owner);
    }

    public OwnerDetailResponseDto getOwnerDetailById(Integer id) {
        OwnerModel owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado pelo id: " + id));

        return OwnerMapper.entityToDetailResponseDto(owner);
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

    public OwnerResponseDto updateOwnerById(Integer id, OwnerUpdateDto dto) {
        OwnerModel owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (isDuplicateFields(dto.getEmail(), dto.getCpf(), dto.getPhoneNumber(), id))
            throw new EntityConflictException("Já existe um usuário com o e-mail, CPF ou telefone informados.");

        owner.setName(dto.getName());
        owner.setEmail(dto.getEmail());
        owner.setCpf(dto.getCpf());
        owner.setPhoneNumber(dto.getPhoneNumber());
        owner.setCep(dto.getCep());
        owner.setNeighborhood(dto.getNeighborhood());
        owner.setStreet(dto.getStreet());
        owner.setNumber(dto.getNumber());
        owner.setComplement(dto.getComplement());
        owner.setLastUpdate(LocalDateTime.now());

        OwnerModel saved = ownerRepository.save(owner);

        return OwnerMapper.entityToResponseDto(saved);
    }

    public OwnerResponseDto updatePasswordById(Integer id, OwnerUpdatePasswordDto dto){
        OwnerModel owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if(!dto.getCurrentPassword().equals(owner.getPassword()))
            throw new EntityUnauthorizedException("Senha atual incorreta");

        owner.setPassword(dto.getNewPassword());
        owner.setLastUpdate(LocalDateTime.now());

        return OwnerMapper.entityToResponseDto(owner);
    }

    public OwnerResponseDto login(OwnerLoginDto ownerLogin){
        OwnerModel owner = ownerRepository.findByEmail(ownerLogin.getEmail())
                .orElseThrow(() -> new EntityUnauthorizedException("Email ou senha inválidos"));

        if(owner.getPassword().equals(ownerLogin.getPassword()))
            return OwnerMapper.entityToResponseDto(owner);

        throw new EntityUnauthorizedException("Email ou senha inválidos");
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