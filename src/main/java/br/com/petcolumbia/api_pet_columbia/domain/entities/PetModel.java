package br.com.petcolumbia.api_pet_columbia.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pet")
public class PetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerModel owner;

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
    private List<AppointmentModel> appointments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OwnerModel getOwnerModel() {
        return owner;
    }

    public void setOwnerModel(OwnerModel ownerModel) {
        this.owner = ownerModel;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<AppointmentModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentModel> appointments) {
        this.appointments = appointments;
    }
}
