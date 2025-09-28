package br.com.petcolumbia.api_pet_columbia.old.dtos.requests.petDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PetUpdateDto {
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;
    @NotBlank(message = "O tamanho é obrigatório")
    @Size(max = 20, message = "O tamanho deve ter no máximo 20 caracteres")
    private String size;
    @NotBlank(message = "A espécie é obrigatório")
    @Size(max = 30, message = "A espécie deve ter no máximo 30 caracteres")
    private String species;
    @NotBlank(message = "O tipo é obrigatório")
    @Size(max = 40, message = "O tipo deve ter no máximo 40 caracteres")
    private String breed;
    @NotBlank(message = "A pelagem é obrigatório")
    @Size(max = 30, message = "A pelagem deve ter no máximo 30 caracteres")
    private String coat;
    @NotBlank(message = "A idade é obrigatório")
    private Integer age;
    @NotBlank(message = "O sexo é obrigatório")
    @Size(max = 10, message = "O sexo deve ter no máximo 10 caracteres")
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
