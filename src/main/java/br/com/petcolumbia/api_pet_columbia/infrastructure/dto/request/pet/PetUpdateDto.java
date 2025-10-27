package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.pet;

import jakarta.validation.constraints.*;

public class PetUpdateDto {
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "O tamanho é obrigatório")
    @Size(max = 20, message = "O tamanho deve ter no máximo 20 caracteres")
    private String size;

    @NotBlank(message = "A espécie é obrigatória")
    @Size(max = 30, message = "A espécie deve ter no máximo 30 caracteres")
    private String species;

    @NotBlank(message = "A raça é obrigatória")
    @Size(max = 40, message = "A raça deve ter no máximo 40 caracteres")
    private String breed;

    @NotBlank(message = "A pelagem é obrigatória")
    @Size(max = 30, message = "A pelagem deve ter no máximo 30 caracteres")
    private String coat;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 0, message = "A idade deve ser no mínimo 0")
    @Max(value = 30, message = "A idade deve ser no máximo 30")
    private Integer age;

    @NotBlank(message = "O sexo é obrigatório")
    @Size(max = 10, message = "O sexo deve ter no máximo 10 caracteres")
    private String sex;

    public PetUpdateDto() {
    }

    public PetUpdateDto(String name, String size, String species, String breed, String coat, Integer age, String sex) {
        this.name = name;
        this.size = size;
        this.species = species;
        this.breed = breed;
        this.coat = coat;
        this.age = age;
        this.sex = sex;
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
}
