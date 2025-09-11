package br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "owner")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String cpf;
    private String phoneNumber;
    private String email;
    private String password;
    private String cep;
    private String neighborhood;
    private String street;
    private String number;
    private String complement;
    private Boolean isAdm;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "owner")
    private List<PetEntity> pets;

    public OwnerEntity() {
    }

    public OwnerEntity(Integer id, String name, String cpf, String phoneNumber, String email, String password, String cep, String neighborhood, String street, String number, String complement, Boolean isAdm, LocalDateTime createdAt, LocalDateTime lastUpdate, List<PetEntity> pets) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.cep = cep;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.isAdm = isAdm;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
        this.pets = pets;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Boolean getAdm() {
        return isAdm;
    }

    public void setAdm(Boolean adm) {
        isAdm = adm;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<PetEntity> getPets() {
        return pets;
    }

    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
    }
}

