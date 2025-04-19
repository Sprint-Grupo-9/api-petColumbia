package br.com.petcolumbia.api_pet_columbia.dtos.mappers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.OwnerLoginDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.OwnerTokenResponseDto;

import java.time.LocalDateTime;

public class OwnerMapper {

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
}
