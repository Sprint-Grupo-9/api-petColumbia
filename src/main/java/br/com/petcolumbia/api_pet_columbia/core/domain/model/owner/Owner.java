package br.com.petcolumbia.api_pet_columbia.core.domain.model.owner;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.Address;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.valueobject.PersonalInfo;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.pet.Pet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {
    private Integer id;
    private String name;
    private PersonalInfo personalInfo;
    private String email;
    private String password;
    private Address address;
    private Boolean isAdm;
    private List<Pet> pets;

    public Owner() {
    }

    public Owner(Integer id, String name, PersonalInfo personalInfo, String email, String password, Address address, Boolean isAdm, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.personalInfo = personalInfo;
        this.email = email;
        this.password = password;
        this.address = address;
        this.isAdm = isAdm;
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

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address address) {
        this.address = address;
    }

    public Boolean getAdm() {
        return isAdm;
    }

    public void setAdm(Boolean adm) {
        isAdm = adm;
    }

    @JsonIgnore
    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
