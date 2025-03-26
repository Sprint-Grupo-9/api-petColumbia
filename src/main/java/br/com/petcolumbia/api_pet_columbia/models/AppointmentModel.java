package br.com.petcolumbia.api_pet_columbia.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetModel pet;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeModel employeeModel;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceModel service;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Boolean isFinished;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

}
