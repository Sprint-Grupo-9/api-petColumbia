package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.employee.EmployeeResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeResponseMapper {

    public static EmployeeResponseDto toResponse(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());

        return dto;
    }

    public static List<EmployeeResponseDto> toResponseList(List<Employee> employees) {
        if (employees == null) {
            return null;
        }

        return employees.stream()
                .map(EmployeeResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}

