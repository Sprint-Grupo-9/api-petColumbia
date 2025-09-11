package br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.procedure.Procedure;
import br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper.association.EmployeeProcedureAssociationEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.ProcedureEntity;

public class ProcedureEntityMapper {

    public static Procedure toDomain(ProcedureEntity entity){
        if(entity == null){
            return null;
        }
        return new Procedure(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                EmployeeProcedureAssociationEntityMapper.toDomainList(entity.getEmployeeProcedures()),
                ProcedurePriceAndDurationEntityMapper.toDomainList(entity.getPricesAndDurations())
        );
    }
}
