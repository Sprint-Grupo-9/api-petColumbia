package br.com.petcolumbia.api_pet_columbia.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityNotFoundException;

class EmployeeServiceTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    /**
     * Verifica se o funcionário é retornado corretamente quando existe um funcionário
     * com o ID especificado.
     */
    @Test
    void testFindEmployeeById_Success() {
        Integer employeeId = 1;
        EmployeeModel employee = new EmployeeModel();
        employee.setId(employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        EmployeeModel result = employeeService.findEmployeeById(employeeId);

        assertNotNull(result);
        assertEquals(employeeId, result.getId());
    }

    /**
     * Garante que uma exceção {@code EntityNotFoundException} é lançada quando
     * o funcionário não é encontrado pelo ID.
     */
    @Test
    void testFindEmployeeById_NotFound() {
        Integer employeeId = 99;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            employeeService.findEmployeeById(employeeId);
        });

        assertEquals("Funcionário não encontrado", ex.getMessage());
    }

    /**
     * Verifica se o método listEmployeesServices retorna corretamente uma lista
     * de funcionários associados aos IDs de serviços fornecidos.
     */
    @Test
    void testListEmployeesServices_ReturnsEmployeesList() {
        List<Integer> serviceIds = List.of(1, 2);
        List<EmployeeModel> employees = new ArrayList<>();

        EmployeeModel emp1 = new EmployeeModel();
        emp1.setId(1);
        EmployeeModel emp2 = new EmployeeModel();
        emp2.setId(2);

        employees.add(emp1);
        employees.add(emp2);

        when(employeeRepository.findEmployeesByServiceIds(serviceIds)).thenReturn(employees);

        List<EmployeeModel> result = employeeService.listEmployeesServices(serviceIds);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        verify(employeeRepository).findEmployeesByServiceIds(serviceIds);
    }

    // Mock classes e service para teste

    interface EmployeeRepository {
        Optional<EmployeeModel> findById(Integer id);
        List<EmployeeModel> findEmployeesByServiceIds(List<Integer> serviceIds);
    }

    static class EmployeeService {
        private final EmployeeRepository employeeRepository;

        public EmployeeService(EmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
        }

        public EmployeeModel findEmployeeById(Integer employeeId) {
            return employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        }

        public List<EmployeeModel> listEmployeesServices(List<Integer> servicesIds){
            return employeeRepository.findEmployeesByServiceIds(servicesIds);
        }
    }

    static class EmployeeModel {
        private Integer id;

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
    }
}
