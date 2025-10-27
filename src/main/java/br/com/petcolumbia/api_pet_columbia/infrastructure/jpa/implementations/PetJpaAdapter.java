package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.implementations;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet.PetGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.command.pet.PetUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityConflictException;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.PetEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.OwnerEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.OwnerJpaRepository;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.PetJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PetJpaAdapter implements PetGateway {

    private final PetJpaRepository petRepository;
    private final OwnerJpaRepository ownerRepository;

    public PetJpaAdapter(PetJpaRepository petRepository, OwnerJpaRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Pet create(Pet pet, Integer ownerId) {
        OwnerEntity owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner não encontrado"));

        PetEntity entity = PetEntityMapper.toEntityWithoutOwner(pet);
        entity.setOwner(owner);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setLastUpdate(LocalDateTime.now());

        PetEntity savedEntity = petRepository.save(entity);
        return PetEntityMapper.toDomainWithoutAppointments(savedEntity);
    }

    @Override
    public Pet findPetById(Integer id, Integer ownerId) {
        Optional<PetEntity> petEntity = petRepository.findById(id);

        if (petEntity.isEmpty()) {
            throw new EntityNotFoundException("Pet não encontrado");
        }

        if (!petEntity.get().getOwner().getId().equals(ownerId)) {
            throw new EntityConflictException("Pet não encontrado para o dono informado");
        }

        return PetEntityMapper.toDomainWithoutAppointments(petEntity.get());
    }

    @Override
    public List<Pet> listAllPetsByOwner(Integer ownerId) {
        OwnerEntity owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner não encontrado"));

        List<PetEntity> petEntities = petRepository.findAllByOwner(owner);

        // Mapeia sem incluir appointments para evitar loops
        return petEntities.stream()
                .map(PetEntityMapper::toDomainWithoutAppointments)
                .toList();
    }

    @Override
    public void deletePetById(Integer id) {
        petRepository.deleteById(id);
    }

    @Override
    public Pet updatePetById(Integer id, PetUpdateCommand command) {
        PetEntity entity = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

        entity.setName(command.name());
        entity.setSize(command.size());
        entity.setSpecies(command.species());
        entity.setBreed(command.breed());
        entity.setCoat(command.coat());
        entity.setAge(command.age());
        entity.setSex(command.sex());
        entity.setLastUpdate(LocalDateTime.now());

        PetEntity savedEntity = petRepository.save(entity);
        return PetEntityMapper.toDomainWithoutAppointments(savedEntity);
    }

    @Override
    public boolean existsById(Integer id) {
        return petRepository.existsById(id);
    }
}

