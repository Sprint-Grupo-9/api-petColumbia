package br.com.petcolumbia.api_pet_columbia.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Specialty {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "specialty")
    private List<ServiceCategoryModel> serviceCategory;

    private String name;
    private String description;
}
