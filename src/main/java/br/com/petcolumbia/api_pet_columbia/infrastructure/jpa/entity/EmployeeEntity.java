package br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity;

import br.com.petcolumbia.api_pet_columbia.infrastructure.jpa.entity.association.EmployeePetOfferingAssociationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "employee")
    private List<AppointmentEntity> appointments;

    @OneToMany(mappedBy = "employee")
    private List<EmployeePetOfferingAssociationEntity> employeePetOfferings;

    public EmployeeEntity() {
    }

    public EmployeeEntity(Integer id, String name, List<AppointmentEntity> appointments, List<EmployeePetOfferingAssociationEntity> employeePetOfferings) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
        this.employeePetOfferings = employeePetOfferings;
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

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }

    public List<EmployeePetOfferingAssociationEntity> getEmployeePetOfferings() {
        return employeePetOfferings;
    }

    public void setEmployeePetOfferings(List<EmployeePetOfferingAssociationEntity> employeePetOfferings) {
        this.employeePetOfferings = employeePetOfferings;
    }
}