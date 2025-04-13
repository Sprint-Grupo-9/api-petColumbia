package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
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


        OwnerModel owner = createDtoToEntity(newOwner);
        ownerRepository.save(owner);

        return entityToResponseDto(owner);
    }

    public OwnerDetailResponseDto getOwnerDetailById(Integer id) {
        OwnerModel owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado pelo id: " + id));

        return entityToDetailResponseDto(owner);
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

        return entityToResponseDto(saved);
    }

    public OwnerResponseDto updatePasswordById(Integer id, OwnerUpdatePasswordDto dto){
        OwnerModel owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if(!dto.getCurrentPassword().equals(owner.getPassword()))
            throw new EntityUnauthorizedException("Senha atual incorreta");

        owner.setPassword(dto.getNewPassword());
        owner.setLastUpdate(LocalDateTime.now());

        return entityToResponseDto(owner);
    }

    public OwnerResponseDto login(OwnerLoginDto ownerLogin){
        OwnerModel owner = ownerRepository.findByEmail(ownerLogin.getEmail())
                .orElseThrow(() -> new EntityUnauthorizedException("Email ou senha inválidos"));

        if(owner.getPassword().equals(ownerLogin.getPassword()))
            return entityToResponseDto(owner);

        throw new EntityUnauthorizedException("Email ou senha inválidos");
    }

    public OwnerModel createDtoToEntity(OwnerCreateDto ownerCreateDto){
        OwnerModel owner = new OwnerModel();

        owner.setName(ownerCreateDto.getName());
        owner.setCpf(ownerCreateDto.getCpf());
        owner.setPhoneNumber(ownerCreateDto.getPhoneNumber());
        owner.setEmail(ownerCreateDto.getEmail());
        owner.setPassword(ownerCreateDto.getPassword());
        owner.setCep(ownerCreateDto.getCep());
        owner.setNeighborhood(ownerCreateDto.getNeighborhood());
        owner.setStreet(ownerCreateDto.getStreet());
        owner.setNumber(ownerCreateDto.getNumber());
        owner.setComplement(ownerCreateDto.getComplement());
        owner.setCreatedAt(LocalDateTime.now());
        owner.setLastUpdate(LocalDateTime.now());

        return owner;
    }

    public OwnerResponseDto entityToResponseDto(OwnerModel owner){
        OwnerResponseDto responseDto = new OwnerResponseDto();
        responseDto.setId(owner.getId());
        responseDto.setName(owner.getName());

        return responseDto;
    }

    private OwnerDetailResponseDto entityToDetailResponseDto(OwnerModel owner) {
        OwnerDetailResponseDto dto = new OwnerDetailResponseDto();
        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setCpf(owner.getCpf());
        dto.setPhoneNumber(owner.getPhoneNumber());
        dto.setEmail(owner.getEmail());
        dto.setCep(owner.getCep());
        dto.setNeighborhood(owner.getNeighborhood());
        dto.setStreet(owner.getStreet());
        dto.setNumber(owner.getNumber());
        dto.setComplement(owner.getComplement());
        return dto;
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