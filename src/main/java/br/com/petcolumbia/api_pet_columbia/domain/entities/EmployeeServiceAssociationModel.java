package br.com.petcolumbia.api_pet_columbia.domain.entities;

import br.com.petcolumbia.api_pet_columbia.domain.entities.helpers.EmployeeServiceAssociationId;
import jakarta.persistence.*;

@Entity
@Table(name = "employees_has_services")
public class EmployeeServiceAssociationModel {
    @EmbeddedId
    private EmployeeServiceAssociationId id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private EmployeeModel employee;

    @ManyToOne
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    private ServiceModel service;

    public EmployeeServiceAssociationId getId() {
        return id;
    }

    public void setId(EmployeeServiceAssociationId id) {
        this.id = id;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public ServiceModel getService() {
        return service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }
}
