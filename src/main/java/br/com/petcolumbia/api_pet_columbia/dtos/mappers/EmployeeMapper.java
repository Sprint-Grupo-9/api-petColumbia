package br.com.petcolumbia.api_pet_columbia.dtos.mappers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.EmployeeResponse;

public class EmployeeMapper {

    public static EmployeeResponse entityToResponse(EmployeeModel employee){
        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setName(employee.getName());

        return response;
    }
}
