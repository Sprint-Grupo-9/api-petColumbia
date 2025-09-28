package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.association.helper;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.associations.valueobject.EmployeeProcedureAssociationId;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.association.helper.EmployeeProcedureEmbeddableId;

public class EmployeeProcedureAssociationIdMapper {

    public static EmployeeProcedureAssociationId toDomain(EmployeeProcedureEmbeddableId id){
        if(id == null){
            return null;
        }
        return new EmployeeProcedureAssociationId(
                id.getEmployeeId(),
                id.getProcedureId()
        );
    }

    public static EmployeeProcedureEmbeddableId toEntity(EmployeeProcedureAssociationId id){
        if(id == null){
            return null;
        }
        return new EmployeeProcedureEmbeddableId(
                id.getEmployeeId(),
                id.getProcedureId()
        );
    }
}
