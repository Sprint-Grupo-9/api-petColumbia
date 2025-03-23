package br.com.petcolumbia.api_pet_columbia.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String cpf;
    private String phoneNumber;
    private String email;
    private String password;
    private LocalDate createdAt;
    private LocalDate lastUpdate;

    @OneToMany(mappedBy = "owner")
    private List<PetModel> pets;

    @OneToOne(mappedBy = "owner")
    private AddressModel address;

}
