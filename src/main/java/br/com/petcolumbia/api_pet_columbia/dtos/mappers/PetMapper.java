package br.com.petcolumbia.api_pet_columbia.dtos.mappers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.PetCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.PetUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.PetResponseDto;

import java.time.LocalDateTime;

public class PetMapper {

    public static PetResponseDto entityToResponse(PetModel pet) {
        PetResponseDto responseDto = new PetResponseDto();

        responseDto.setId(pet.getId());
        responseDto.setOwnerId(pet.getOwnerModel().getId());
        responseDto.setName(pet.getName());
        responseDto.setSize(pet.getSize());
        responseDto.setSpecies(pet.getSpecies());
        responseDto.setBreed(pet.getBreed());
        responseDto.setCoat(pet.getCoat());
        responseDto.setAge(pet.getAge());
        responseDto.setSex(pet.getSex());

        return responseDto;
    }

    public static PetModel createDtoToEntity(PetCreateDto dto) {
        PetModel pet = new PetModel();

        pet.setName(dto.getName());
        pet.setSize(dto.getSize());
        pet.setSpecies(dto.getSpecies());
        pet.setBreed(dto.getBreed());
        pet.setCoat(dto.getCoat());
        pet.setAge(dto.getAge());
        pet.setSex(dto.getSex());
        pet.setCreatedAt(LocalDateTime.now());
        pet.setLastUpdate(LocalDateTime.now());

        return pet;
    }
    
    public static PetModel updateDtoToEntity(PetUpdateDto dto) {
        PetModel pet = new PetModel();

        pet.setName(dto.getName());
        pet.setSize(dto.getSize());
        pet.setSpecies(dto.getSpecies());
        pet.setBreed(dto.getBreed());
        pet.setCoat(dto.getCoat());
        pet.setAge(dto.getAge());
        pet.setSex(dto.getSex());
        pet.setLastUpdate(LocalDateTime.now());

        return pet;
    }
}
