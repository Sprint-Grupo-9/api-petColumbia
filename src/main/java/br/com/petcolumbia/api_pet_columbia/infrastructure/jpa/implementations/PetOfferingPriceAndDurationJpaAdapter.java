package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.implementations;

import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingGateway;
import br.com.petcolumbia.api_pet_columbia.core.adapter.pet_offering.PetOfferingPriceAndDurationGateway;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet_offering.PetOfferingWithPriceResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.exception.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet_offering_price_and_duration.PetOfferingPriceAndDuration;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.PetOfferingPriceAndDurationEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingContext;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common.MappingStrategy;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.PetOfferingPriceAndDurationEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.PetJpaRepository;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.repository.PetOfferingPriceAndDurationJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PetOfferingPriceAndDurationJpaAdapter implements PetOfferingPriceAndDurationGateway {

    private final PetOfferingPriceAndDurationJpaRepository priceAndDurationRepository;
    private final PetOfferingGateway petOfferingGateway;
    private final PetJpaRepository petRepository;

    public PetOfferingPriceAndDurationJpaAdapter(
            PetOfferingPriceAndDurationJpaRepository priceAndDurationRepository,
            PetOfferingGateway petOfferingGateway,
            PetJpaRepository petRepository
    ) {
        this.priceAndDurationRepository = priceAndDurationRepository;
        this.petOfferingGateway = petOfferingGateway;
        this.petRepository = petRepository;
    }

    @Override
    public PetOfferingPriceAndDuration findByPetOfferingIdAndPetSizeAndCoat(
            Integer petOfferingId,
            String petSize,
            String petCoat
    ) {
        PetOfferingPriceAndDurationEntity entity = priceAndDurationRepository
                .findByPetOfferingIdAndPetSizeAndPetCoat(petOfferingId, petSize, petCoat);

        if (entity == null) {
            throw new EntityNotFoundException("Preço e duração não encontrados para este serviço e tipo de pet");
        }

        // Map without petOffering to avoid circular references
        MappingContext context = new MappingContext(new MappingStrategy.ShallowMapping());
        return PetOfferingPriceAndDurationEntityMapper.toDomain(entity, context);
    }

    @Override
    public List<PetOfferingWithPriceResponseDto> getPetOfferingsPricesByPetIdAndPetOfferingIds(
            Integer petId,
            List<Integer> petOfferingIds
    ) {
        PetEntity petEntity = petRepository.findById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

        List<PetOfferingWithPriceResponseDto> response = new ArrayList<>();

        for (Integer petOfferingId : petOfferingIds) {
            PetOfferingPriceAndDuration priceAndDuration = findByPetOfferingIdAndPetSizeAndCoat(
                    petOfferingId,
                    petEntity.getSize(),
                    petEntity.getCoat()
            );

            String petOfferingName = petOfferingGateway.getPetOfferingsNamesByIds(
                    Collections.singletonList(petOfferingId)
            );

            response.add(new PetOfferingWithPriceResponseDto(
                    petOfferingId,
                    petOfferingName,
                    priceAndDuration.getPrice()
            ));
        }

        if (response.isEmpty()) {
            throw new EntityNotFoundException("Procedimentos não encontrados para o tipo do pet.");
        }

        return response;
    }

    @Override
    public Double calculateTotalPrice(List<Integer> petOfferingIds, String petSize, String petCoat) {
        return petOfferingIds.stream()
                .mapToDouble(petOfferingId ->
                        findByPetOfferingIdAndPetSizeAndCoat(petOfferingId, petSize, petCoat).getPrice())
                .sum();
    }

    @Override
    public Integer calculateTotalDuration(List<Integer> petOfferingIds, String petSize, String petCoat) {
        return petOfferingIds.stream()
                .mapToInt(petOfferingId ->
                        findByPetOfferingIdAndPetSizeAndCoat(petOfferingId, petSize, petCoat).getDuration())
                .sum();
    }
}

