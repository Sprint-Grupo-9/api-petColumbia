package br.com.petcolumbia.api_pet_columbia.core.domain.model.employee;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.appointment.Appointment;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.associations.EmployeeProcedureAssociation;

import java.util.List;

public class Employee {
    private Integer id;
    private String name;
    private List<Appointment> appointments;
    private List<EmployeeProcedureAssociation> employeeProcedures;

    public Employee() {
    }

    public Employee(Integer id, String name, List<Appointment> appointments, List<EmployeeProcedureAssociation> employeeProcedures) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
        this.employeeProcedures = employeeProcedures;
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

    public List<EmployeeProcedureAssociation> getEmployeeProcedures() {
        return employeeProcedures;
    }

    public void setEmployeeProcedures(List<EmployeeProcedureAssociation> employeeProcedures) {
        this.employeeProcedures = employeeProcedures;
    }
}
