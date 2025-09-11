package br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.procedure_price_and_duration.ProcedurePriceAndDuration;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.ProcedurePriceAndDurationEntity;

import java.util.List;

public class ProcedurePriceAndDurationEntityMapper {

    public static ProcedurePriceAndDuration toDomain(ProcedurePriceAndDurationEntity entity){
        if(entity == null){
            return null;
        }
        return new ProcedurePriceAndDuration(
                entity.getId(),
                ProcedureEntityMapper.toDomain(entity.getProcedure()),
                entity.getPetSize(),
                entity.getPetCoat(),
                entity.getPrice(),
                entity.getDuration()
        );
    }

    public static List<ProcedurePriceAndDuration> toDomainList(List<ProcedurePriceAndDurationEntity> entities){
        if(entities == null){
            return null;
        }
        return entities.stream().map(ProcedurePriceAndDurationEntityMapper::toDomain).toList();
    }
}
