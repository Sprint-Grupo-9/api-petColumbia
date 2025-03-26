package br.com.petcolumbia.api_pet_columbia.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class EmployeeModel {
    @Id
    private Integer id;
    private String name;
    private Boolean isPresent;

    @OneToMany(mappedBy = "employee")
    private List<AppointmentModel> appointments;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeServiceAssociationModel> employeeServices;
}
