package br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper.association;

import br.com.petcolumbia.api_pet_columbia.core.domain.associations.EmployeeProcedureAssociation;
import br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper.association.helper.EmployeeProcedureAssociationIdMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper.EmployeeEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper.ProcedureEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.association.EmployeeProcedureAssociationEntity;

import java.util.List;

public class EmployeeProcedureAssociationEntityMapper {

    public static EmployeeProcedureAssociation toDomain(EmployeeProcedureAssociationEntity entity){
        if(entity == null){
            return null;
        }
        return new EmployeeProcedureAssociation(
                EmployeeProcedureAssociationIdMapper.toDomain(entity.getId()),
                EmployeeEntityMapper.toDomain(entity.getEmployee()),
                ProcedureEntityMapper.toDomain(entity.getProcedure())
        );
    }

    public static List<EmployeeProcedureAssociation> toDomainList(List<EmployeeProcedureAssociationEntity> entities){
        if(entities == null){
            return null;
        }
        return entities.stream().map(EmployeeProcedureAssociationEntityMapper::toDomain).toList();
    }
}
