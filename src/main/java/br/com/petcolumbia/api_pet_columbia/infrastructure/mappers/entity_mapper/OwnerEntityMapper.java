package br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject.Adress;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject.PersonalInfo;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.OwnerEntity;
import br.com.petcolumbia.api_pet_columbia.old.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.old.dtos.requests.ownerDtos.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.requests.ownerDtos.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.requests.ownerDtos.OwnerUpdateDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.ownerDtos.OwnerInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.ownerDtos.OwnerResponseDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.ownerDtos.OwnerTokenResponseDto;

import java.time.LocalDateTime;

public class OwnerEntityMapper {

    public static OwnerInfoResponseDto to(Owner owner){
        OwnerInfoResponseDto dto = new OwnerInfoResponseDto();
        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setCpf(owner.getPersonalInfo().getCpf());
        dto.setPhoneNumber(owner.getPersonalInfo().getPhoneNumber());
        dto.setEmail(owner.getEmail());
        dto.setCep(owner.getAdress().getCep());
        dto.setNeighborhood(owner.getAdress().getNeighborhood());
        dto.setStreet(owner.getAdress().getStreet());
        dto.setNumber(owner.getAdress().getNumber());
        dto.setComplement(owner.getAdress().getComplement());

        return dto;
    }

    public static Owner of(OwnerEntity entity){
        if (entity == null) {
            return null;
        }

        Owner owner = new Owner(
                entity.getId(),
                entity.getName(),
                new PersonalInfo(entity.getCpf(), entity.getPhoneNumber()),
                entity.getEmail(),
                entity.getPassword(),
                new Adress(entity.getCep(), entity.getNeighborhood(), entity.getStreet(), entity.getNumber(), entity.getComplement()),
                entity.getAdm(),
                PetEntityMapper.toDomainList(entity.getPets())
        );

        return owner;
    }

    public static OwnerModel createDtoToEntity(OwnerCreateDto ownerCreateDto){
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

    public static OwnerModel of(OwnerLoginDto ownerLoginDto){
        OwnerModel owner = new OwnerModel();

        owner.setEmail(ownerLoginDto.getEmail());
        owner.setPassword(ownerLoginDto.getPassword());

        return owner;
    }

    public static OwnerTokenResponseDto of(OwnerModel owner, String token){
        OwnerTokenResponseDto ownerToken = new OwnerTokenResponseDto();

        ownerToken.setId(owner.getId());
        ownerToken.setEmail(owner.getEmail());
        ownerToken.setName(owner.getName());
        ownerToken.setToken(token);
        ownerToken.setAdmin(owner.getAdm());

        return ownerToken;
    }

    public static OwnerResponseDto entityToResponseDto(OwnerModel owner){
        OwnerResponseDto responseDto = new OwnerResponseDto();
        responseDto.setId(owner.getId());
        responseDto.setName(owner.getName());
        responseDto.setEmail(owner.getEmail());

        return responseDto;
    }

    public static OwnerInfoResponseDto entityToDetailResponseDto(OwnerModel owner) {
        OwnerInfoResponseDto dto = new OwnerInfoResponseDto();
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

    public static OwnerModel updateDtoToEntity(OwnerUpdateDto updatedOwner){
        OwnerModel owner = new OwnerModel();

        owner.setEmail(updatedOwner.getEmail());
        owner.setPhoneNumber(updatedOwner.getPhoneNumber());
        owner.setCep(updatedOwner.getCep());
        owner.setNeighborhood(updatedOwner.getNeighborhood());
        owner.setStreet(updatedOwner.getStreet());
        owner.setNumber(updatedOwner.getNumber());
        owner.setComplement(updatedOwner.getComplement());
        owner.setLastUpdate(LocalDateTime.now());

        return owner;
    }
}
