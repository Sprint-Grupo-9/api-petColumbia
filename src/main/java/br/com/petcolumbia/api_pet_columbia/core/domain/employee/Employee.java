package br.com.petcolumbia.api_pet_columbia.core.domain.employee;

import br.com.petcolumbia.api_pet_columbia.core.domain.appointment.Appointment;
import br.com.petcolumbia.api_pet_columbia.core.domain.associations.EmployeeProcedureAssociation;

import java.util.List;

public class Employee {
    private Integer id;
    private String name;
    private List<Appointment> appointments;
    private List<EmployeeProcedureAssociation> employeeServices;

    public Employee() {
    }

    public Employee(Integer id, String name, List<Appointment> appointments, List<EmployeeProcedureAssociation> employeeServices) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
        this.employeeServices = employeeServices;
    }

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

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<EmployeeProcedureAssociation> getEmployeeServices() {
        return employeeServices;
    }

    public void setEmployeeServices(List<EmployeeProcedureAssociation> employeeServices) {
        this.employeeServices = employeeServices;
    }
}
