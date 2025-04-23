package br.com.petcolumbia.api_pet_columbia.dtos.responses;

public class PetResponseDto {
    private Integer id;
    private Integer ownerId;
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

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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

    public Integer getOwnerId() {
        return ownerId;
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
