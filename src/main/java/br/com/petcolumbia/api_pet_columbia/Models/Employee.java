package br.com.petcolumbia.api_pet_columbia.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {
    @Id
    private Integer id;

    @OneToMany(mappedBy = "employee")
    private List<Specialty> specialty;

    private String name;
    private Boolean isPresent;


}
