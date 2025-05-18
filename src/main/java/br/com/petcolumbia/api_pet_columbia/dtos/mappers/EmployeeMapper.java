package br.com.petcolumbia.api_pet_columbia.dtos.mappers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.employeeDtos.EmployeeResponseDto;

public class EmployeeMapper {

    public static EmployeeResponseDto entityToResponse(EmployeeModel employee){
        EmployeeResponseDto response = new EmployeeResponseDto();

        response.setId(employee.getId());
        response.setName(employee.getName());

        return response;
    }
}
