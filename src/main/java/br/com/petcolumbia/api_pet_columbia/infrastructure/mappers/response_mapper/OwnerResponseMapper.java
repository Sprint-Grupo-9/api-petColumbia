package br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.response_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.ownerDtos.OwnerResponseDto;

public class OwnerResponseMapper {

    public static OwnerResponseDto of(Owner owner){
        return new OwnerResponseDto(
                owner.getId(),
                owner.getName(),
                owner.getEmail()
        );
    }
}
