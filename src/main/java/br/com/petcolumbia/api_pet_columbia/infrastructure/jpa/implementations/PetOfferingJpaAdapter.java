package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.implementations;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering.PetOffering;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.PetOfferingEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetOfferingEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.PetOfferingJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetOfferingJpaAdapter implements PetOfferingGateway {

    private final PetOfferingJpaRepository petOfferingRepository;

    public PetOfferingJpaAdapter(PetOfferingJpaRepository petOfferingRepository) {
        this.petOfferingRepository = petOfferingRepository;
    }

    @Override
    public List<PetOffering> listAllPetOfferings() {
        List<PetOfferingEntity> entities = petOfferingRepository.findAll();

        // Map without relations to avoid circular references
        MappingContext context = new MappingContext(new MappingStrategy.ShallowMapping());
        return PetOfferingEntityMapper.toDomainList(entities, context);
    }

    @Override
    public PetOffering findPetOfferingById(Integer petOfferingId) {
        PetOfferingEntity entity = petOfferingRepository.findById(petOfferingId)
                .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado"));

        // Map without relations to avoid circular references
        MappingContext context = new MappingContext(new MappingStrategy.ShallowMapping());
        return PetOfferingEntityMapper.toDomain(entity, context);
    }

    @Override
    public String getPetOfferingsNamesByIds(List<Integer> petOfferingIds) {
        return petOfferingRepository.findAllById(petOfferingIds)
                .stream()
                .map(PetOfferingEntity::getName)
                .collect(Collectors.joining(", "));
    }

    @Override
    public List<Integer> getPetOfferingIds(List<PetOffering> petOfferings) {
        return petOfferings.stream()
                .map(PetOffering::getId)
                .collect(Collectors.toList());
    }
}

