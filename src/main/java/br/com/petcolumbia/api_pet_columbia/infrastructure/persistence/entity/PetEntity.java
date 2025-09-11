package br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pet")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    private String name;
    private String size;
    private String species;
    private String breed;
    private String coat;
    private Integer age;
    private String sex;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "pet")
    private List<AppointmentEntity> appointments;

    public PetEntity() {
    }

    public PetEntity(Integer id, OwnerEntity owner, String name, String size, String species, String breed, String coat, Integer age, String sex, LocalDateTime createdAt, LocalDateTime lastUpdate, List<AppointmentEntity> appointments) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.size = size;
        this.species = species;
        this.breed = breed;
        this.coat = coat;
        this.age = age;
        this.sex = sex;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
        this.appointments = appointments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCoat() {
        return coat;
    }

    public void setCoat(String coat) {
        this.coat = coat;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }
}
