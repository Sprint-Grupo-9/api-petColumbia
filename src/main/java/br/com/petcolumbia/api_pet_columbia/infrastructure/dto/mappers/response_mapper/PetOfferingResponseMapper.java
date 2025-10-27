package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet_offering.PetOfferingResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering.PetOffering;

import java.util.List;
import java.util.stream.Collectors;

public class PetOfferingResponseMapper {

    public static PetOfferingResponseDto toResponse(PetOffering petOffering) {
        if (petOffering == null) {
            return null;
        }

        PetOfferingResponseDto dto = new PetOfferingResponseDto();
        dto.setId(petOffering. getId());
        dto.setName(petOffering.getName());
        dto.setDescription(petOffering.getDescription());

        return dto;
    }

    public static List<PetOfferingResponseDto> toResponseList(List<PetOffering> petOfferings) {
        if (petOfferings == null) {
            return null;
        }

        return petOfferings.stream()
                .map(PetOfferingResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}

