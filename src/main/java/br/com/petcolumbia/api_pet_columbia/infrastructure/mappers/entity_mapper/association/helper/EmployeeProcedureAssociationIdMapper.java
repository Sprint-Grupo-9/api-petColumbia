package br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper.association.helper;

import br.com.petcolumbia.api_pet_columbia.core.domain.associations.valueobject.EmployeeProcedureAssociationId;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.association.helper.EmployeeProcedureEmbeddableId;

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
}
