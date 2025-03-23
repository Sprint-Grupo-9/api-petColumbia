package br.com.petcolumbia.api_pet_columbia.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class OwnerModel {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<PetModel> getPets() {
        return pets;
    }

    public void setPets(List<PetModel> pets) {
        this.pets = pets;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}
