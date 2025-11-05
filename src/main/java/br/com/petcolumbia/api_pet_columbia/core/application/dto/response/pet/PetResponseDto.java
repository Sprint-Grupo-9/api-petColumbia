package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.pet;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.owner.OwnerResponseDto;

public class PetResponseDto {
    private Integer id;
    private OwnerResponseDto owner;
    private String name;
    private String size;
    private String species;
    private String breed;
    private String coat;
    private Integer age;
    private String sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOwner(OwnerResponseDto owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setCoat(String coat) {
        this.coat = coat;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public OwnerResponseDto getOwner() {
        return owner;
    }

    public String getSize() {
        return size;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public String getCoat() {
        return coat;
    }

    public Integer getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }
}
