package br.com.petcolumbia.api_pet_columbia.infrastructure.web;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.employee.EmployeeResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.employee.FindEmployeeByIdUseCase;
import br.com.petcolumbia.api_pet_columbia.core.application.usecase.employee.ListEmployeesByPetOfferingsUseCase;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.employee.Employee;
import br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.response_mapper.EmployeeResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final FindEmployeeByIdUseCase findEmployeeByIdUseCase;
    private final ListEmployeesByPetOfferingsUseCase listEmployeesByPetOfferingsUseCase;

    public EmployeeController(
            FindEmployeeByIdUseCase findEmployeeByIdUseCase,
            ListEmployeesByPetOfferingsUseCase listEmployeesByPetOfferingsUseCase
    ) {
        this.findEmployeeByIdUseCase = findEmployeeByIdUseCase;
        this.listEmployeesByPetOfferingsUseCase = listEmployeesByPetOfferingsUseCase;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um funcionário pelo id")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Integer id) {
        Employee employee = findEmployeeByIdUseCase.execute(id);
        return ResponseEntity.ok(EmployeeResponseMapper.toResponse(employee));
    }

    @GetMapping("/by-pet-offerings")
    @Operation(summary = "Lista funcionários que oferecem todos os serviços especificados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeesByPetOfferings(
            @RequestParam List<Integer> petOfferingIds
    ) {
        List<Employee> employees = listEmployeesByPetOfferingsUseCase.execute(petOfferingIds);

        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EmployeeResponseDto> responseList = EmployeeResponseMapper.toResponseList(employees);
        return ResponseEntity.ok(responseList);
    }
}

