package br.com.petcolumbia.api_pet_columbia.core.domain.owner;

import br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject.Adress;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject.PersonalInfo;
import br.com.petcolumbia.api_pet_columbia.core.domain.pet.Pet;

import java.util.List;

public class Owner {
    private Integer id;
    private String name;
    private PersonalInfo personalInfo;
    private String email;
    private String password;
    private Adress adress;
    private Boolean isAdm;
    private List<Pet> pets;

    public Owner() {
    }

    public Owner(Integer id, String name, PersonalInfo personalInfo, String email, String password, Adress adress, Boolean isAdm, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.personalInfo = personalInfo;
        this.email = email;
        this.password = password;
        this.adress = adress;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Boolean getAdm() {
        return isAdm;
    }

    public void setAdm(Boolean adm) {
        isAdm = adm;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
