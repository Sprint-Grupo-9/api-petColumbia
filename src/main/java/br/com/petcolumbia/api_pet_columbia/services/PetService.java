package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.OwnerModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.PetMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.PetCreateUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.PetResponseDto;
import br.com.petcolumbia.api_pet_columbia.repositories.IOwnerRepository;
import br.com.petcolumbia.api_pet_columbia.repositories.IPetRepository;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final IPetRepository petRepository;
    private final OwnerService ownerService;

    public PetService(IPetRepository petRepository, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
    }

    public PetResponseDto createPet(Integer ownerId, PetCreateUpdateDto dto){
        PetModel pet = PetMapper.createDtoToEntity(dto);

        OwnerModel owner = ownerService.getOwnerById(ownerId);

        pet.setOwnerModel(owner);
        petRepository.save(pet);

        return PetMapper.entityToResponse(pet);
    }

    public PetResponseDto findPetById(@PathVariable Integer id) {
        PetModel pet  = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

        return PetMapper.entityToResponse(pet);
    }

    public List<PetResponseDto> listAllPetsByOwner(Integer ownerId) {

        OwnerModel owner = ownerService.getOwnerById(ownerId);

        List<PetModel> pets = petRepository.findAllByOwner(owner);

        if(pets.isEmpty())
            return new ArrayList<>();

        return pets.stream()
                .map(PetMapper::entityToResponse)
                .toList();
    }

    public void deletePetById(Integer id) {
        if(!petRepository.existsById(id))
            throw new EntityNotFoundException("Pet não encontrado");

        petRepository.deleteById(id);
    }

    public PetResponseDto updatePetById(Integer id, PetCreateUpdateDto dto) {
        PetModel pet  = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

        pet.setName(dto.getName());
        pet.setSize(dto.getSize());
        pet.setSpecies(dto.getSpecies());
        pet.setType(dto.getType());
        pet.setCoat(dto.getCoat());
        pet.setAge(dto.getAge());
        pet.setSex(dto.getSex());
        pet.setLastUpdate(LocalDateTime.now());

        petRepository.save(pet);

        return PetMapper.entityToResponse(pet);
    }
}
