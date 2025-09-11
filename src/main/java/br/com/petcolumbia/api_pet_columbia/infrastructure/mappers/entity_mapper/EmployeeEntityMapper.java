package br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper;

import br.com.petcolumbia.api_pet_columbia.core.domain.employee.Employee;
import br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper.association.EmployeeProcedureAssociationEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.EmployeeEntity;

public class EmployeeEntityMapper {

    public static Employee toDomain(EmployeeEntity entity){
        if(entity == null){
            return null;
        }
        return new Employee(
                entity.getId(),
                entity.getName(),
                AppointmentEntityMapper.toDomainList(entity.getAppointments()),
                EmployeeProcedureAssociationEntityMapper.toDomainList(entity.getEmployeeProcedures())
        );
    }
}
