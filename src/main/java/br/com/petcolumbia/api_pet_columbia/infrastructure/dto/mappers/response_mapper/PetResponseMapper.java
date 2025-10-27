package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet.PetOwnerInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet.PetResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;

import java.util.List;
import java.util.stream.Collectors;

public class PetResponseMapper {

    public static PetResponseDto toResponse(Pet pet) {
        PetResponseDto dto = new PetResponseDto();
        dto.setId(pet.getId());

        if (pet.getOwner() != null) {
            dto.setOwner(OwnerResponseMapper.toResponse(pet.getOwner()));
        }

        dto.setName(pet.getName());
        dto.setSize(pet.getSize());
        dto.setSpecies(pet.getSpecies());
        dto.setBreed(pet.getBreed());
        dto.setCoat(pet.getCoat());
        dto.setAge(pet.getAge());
        dto.setSex(pet.getSex());

        return dto;
    }

    public static PetOwnerInfoResponseDto toOwnerInfoResponse(Pet pet) {
        PetOwnerInfoResponseDto dto = new PetOwnerInfoResponseDto();
        dto.setId(pet.getId());

        if (pet.getOwner() != null) {
            dto.setOwner(OwnerResponseMapper.toInfoResponse(pet.getOwner()));
        }

        dto.setName(pet.getName());
        dto.setSize(pet.getSize());
        dto.setSpecies(pet.getSpecies());
        dto.setBreed(pet.getBreed());
        dto.setCoat(pet.getCoat());
        dto.setAge(pet.getAge());
        dto.setSex(pet.getSex());

        return dto;
    }

    public static List<PetResponseDto> toResponseList(List<Pet> pets) {
        return pets.stream()
                .map(PetResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}

