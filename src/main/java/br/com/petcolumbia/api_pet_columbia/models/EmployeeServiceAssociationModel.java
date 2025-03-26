package br.com.petcolumbia.api_pet_columbia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "employees_has_services")
public class EmployeeServiceAssociationModel {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeModel employee;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceModel service;

    public Integer getId() {
        return id;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public ServiceModel getService() {
        return service;
    }
}
