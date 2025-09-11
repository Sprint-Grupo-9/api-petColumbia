package br.com.petcolumbia.api_pet_columbia.old.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employee")
public class EmployeeModel {
    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "employee")
    private List<AppointmentModel> appointments;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeServiceAssociationModel> employeeServices;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AppointmentModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentModel> appointments) {
        this.appointments = appointments;
    }

    public List<EmployeeServiceAssociationModel> getEmployeeServices() {
        return employeeServices;
    }

    public void setEmployeeServices(List<EmployeeServiceAssociationModel> employeeServices) {
        this.employeeServices = employeeServices;
    }
}
