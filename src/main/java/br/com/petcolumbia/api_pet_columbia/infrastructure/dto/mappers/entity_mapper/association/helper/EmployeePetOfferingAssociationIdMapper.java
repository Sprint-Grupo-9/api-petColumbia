package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.association.helper;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.associations.valueobject.EmployeePetOfferingAssociationId;
import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.association.helper.EmployeePetOfferingEmbeddableId;

public class EmployeePetOfferingAssociationIdMapper {

    public static EmployeePetOfferingAssociationId toDomain(EmployeePetOfferingEmbeddableId id){
        if(id == null){
            return null;
        }
        return new EmployeePetOfferingAssociationId(
                id.getEmployeeId(),
                id.getPetOfferingId()
        );
    }

    public static EmployeePetOfferingEmbeddableId toEntity(EmployeePetOfferingAssociationId id){
        if(id == null){
            return null;
        }
        return new EmployeePetOfferingEmbeddableId(
                id.getEmployeeId(),
                id.getPetOfferingId()
        );
    }
}
