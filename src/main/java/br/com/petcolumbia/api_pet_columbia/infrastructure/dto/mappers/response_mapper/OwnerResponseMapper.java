package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.owner.OwnerInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.owner.OwnerResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.owner.OwnerTokenResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.response.OwnerDetailsDto;

public class OwnerResponseMapper {

    public static OwnerInfoResponseDto toInfoResponse(Owner owner) {
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

    public static OwnerResponseDto toResponse(Owner owner){
        return new OwnerResponseDto(
                owner.getId(),
                owner.getName(),
                owner.getEmail()
        );
    }

    public static OwnerTokenResponseDto toTokenResponse(Owner owner, String token){
        OwnerTokenResponseDto ownerToken = new OwnerTokenResponseDto();

        ownerToken.setId(owner.getId());
        ownerToken.setEmail(owner.getEmail());
        ownerToken.setName(owner.getName());
        ownerToken.setToken(token);
        ownerToken.setAdmin(owner.getAdm());

        return ownerToken;
    }

    public static OwnerDetailsDto toDetailsDto(Owner owner) {
        return new OwnerDetailsDto(
                owner.getName(),
                owner.getEmail(),
                owner.getPassword()
        );
    }

}
