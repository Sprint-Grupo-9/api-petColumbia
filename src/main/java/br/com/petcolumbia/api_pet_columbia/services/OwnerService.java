package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerCreateUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerResponseDto;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityConflictException;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityUnauthorizedException;
import br.com.petcolumbia.api_pet_columbia.repositories.IOwnerRepository;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {

    private final IOwnerRepository ownerRepository;

    public OwnerService(IOwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public OwnerResponseDto createOwner(OwnerCreateUpdateDto newOwner){
        validateOwnerFields(newOwner.getEmail(), newOwner.getCpf(), newOwner.getPhoneNumber());

        OwnerModel owner = toEntity(newOwner);
        ownerRepository.save(owner);

        return toResponseDto(owner);
    }

    public void deleteOwnerById(Integer id) {
        if(!ownerRepository.existsById(id))
            throw new EntityNotFoundException("Não encontrado usuário com id:" + id);

        ownerRepository.deleteById(id);
    }

    public OwnerResponseDto updateOwnerById(Integer id, OwnerCreateUpdateDto owner) {
        if(!ownerRepository.existsById(id))
            throw new EntityNotFoundException("Não encontrado usuário com id:" + id);

        validateOwnerFields(owner.getEmail(), owner.getCpf(), owner.getPhoneNumber(), id);

        OwnerModel updatedOwner = toEntity(owner);

        updatedOwner.setId(id);

        ownerRepository.save(updatedOwner);

        return toResponseDto(updatedOwner);
    }

    public OwnerResponseDto login(OwnerLoginDto ownerLogin){
        OwnerModel owner = ownerRepository.findByEmail(ownerLogin.getEmail())
                .orElseThrow(() -> new EntityUnauthorizedException("Email ou senha inválidos"));

        if(owner.getPassword().equals(ownerLogin.getPassword()))
            return toResponseDto(owner);

        throw new EntityUnauthorizedException("Email ou senha inválidos");
    }

    public OwnerModel toEntity(OwnerCreateUpdateDto ownerCreateUpdateDto){
        OwnerModel owner = new OwnerModel();
        owner.setName(ownerCreateUpdateDto.getName());
        owner.setCpf(ownerCreateUpdateDto.getCpf());
        owner.setPhoneNumber(ownerCreateUpdateDto.getPhoneNumber());
        owner.setEmail(ownerCreateUpdateDto.getEmail());
        owner.setPassword(ownerCreateUpdateDto.getPassword());
        owner.setCep(ownerCreateUpdateDto.getCep());
        owner.setNeighborhood(ownerCreateUpdateDto.getNeighborhood());
        owner.setStreet(ownerCreateUpdateDto.getStreet());
        owner.setNumber(ownerCreateUpdateDto.getNumber());
        owner.setComplement(ownerCreateUpdateDto.getComplement());

        return owner;
    }

    public OwnerResponseDto toResponseDto(OwnerModel owner){
        OwnerResponseDto responseDto = new OwnerResponseDto();
        responseDto.setId(owner.getId());
        responseDto.setName(owner.getName());

        return responseDto;
    }

    public void validateOwnerFields(String email, String cpf, String phoneNumber) {
        List<String> conflictFields = new ArrayList<>();

        if (ownerRepository.existsByEmail(email))
            conflictFields.add("Email: " + email);

        if (ownerRepository.existsByCpf(cpf))
            conflictFields.add("Cpf: " + cpf);

        if (ownerRepository.existsByPhoneNumber(phoneNumber))
            conflictFields.add("PhoneNumber: " + phoneNumber);

        if (!conflictFields.isEmpty()) {
            String mensagem = "Já existe um usuário com os dados: " + String.join(", ", conflictFields);
            throw new EntityConflictException(mensagem);
        }
    }

    public void validateOwnerFields(String email, String cpf, String phoneNumber, Integer id) {
        List<String> conflictFields = new ArrayList<>();

        if (ownerRepository.existsByEmailAndIdNot(email, id))
            conflictFields.add("Email: " + email);

        if (ownerRepository.existsByCpfAndIdNot(cpf, id))
            conflictFields.add("Cpf: " + cpf);

        if (ownerRepository.existsByPhoneNumberAndIdNot(phoneNumber, id))
            conflictFields.add("PhoneNumber: " + phoneNumber);

        if (!conflictFields.isEmpty()) {
            String mensagem = "Já existe um usuário com os dados: " + String.join(", ", conflictFields);
            throw new EntityConflictException(mensagem);
        }
    }
}