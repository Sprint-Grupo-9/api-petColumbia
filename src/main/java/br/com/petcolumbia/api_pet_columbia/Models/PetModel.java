package br.com.petcolumbia.api_pet_columbia.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class PetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private String name;
    private String size;
    private String species;
    private String type;
    private String coat;
    private Integer age;
    private String sex;
    private LocalDate createdAt;
    private LocalDate lastUpdate;

    @OneToMany(mappedBy = "pet")
    private List<AppointmentsModel> appointments;

}
