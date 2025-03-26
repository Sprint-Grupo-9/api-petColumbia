package br.com.petcolumbia.api_pet_columbia.models;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "service")
@Entity
public class ServiceModel {
    @Id
    private Integer id;
    private String name;
    private String description;
    private String petSize;
    private String petCoat;
    private Double price;
    private Double durationInMin;

    @OneToMany(mappedBy = "service")
    private List<AppointmentModel> appointments;

    @OneToMany(mappedBy = "service")
    private List<EmployeeServiceAssociationModel> employeeServices;
}
