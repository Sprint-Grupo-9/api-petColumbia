package br.com.petcolumbia.api_pet_columbia.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityNotFoundException;

class PetServiceTest {
    private PetRepository petRepository;
    private PetService petService;

    @BeforeEach
    void setUp() {
        petRepository = mock(PetRepository.class);
        petService = new PetService(petRepository);
    }

    /**
     * Testes para o método {@link PetService#updatePetById(Integer, PetModel)}.
     *
     * <p><b>1. Atualização bem-sucedida:</b>
     * Verifica se o método atualiza corretamente os dados do pet existente,
     * atualizando todos os campos e definindo o campo {@code lastUpdate} com o momento atual.
     * Confirma que os valores retornados refletem a atualização.</p>
     */
    @Test
    void testUpdatePetById_Success() {
        Integer petId = 1;

        PetModel existingPet = new PetModel();
        existingPet.setId(petId);
        existingPet.setName("Rex");
        existingPet.setSize("Médio");
        existingPet.setSpecies("Cachorro");
        existingPet.setBreed("Labrador");
        existingPet.setCoat("Curto");
        existingPet.setAge(5);
        existingPet.setSex("M");
        existingPet.setLastUpdate(LocalDateTime.now().minusDays(1));

        PetModel updatedPet = new PetModel();
        updatedPet.setName("Rex Updated");
        updatedPet.setSize("Grande");
        updatedPet.setSpecies("Cachorro");
        updatedPet.setBreed("Golden Retriever");
        updatedPet.setCoat("Longo");
        updatedPet.setAge(6);
        updatedPet.setSex("M");

        when(petRepository.findById(petId)).thenReturn(Optional.of(existingPet));
        when(petRepository.save(any(PetModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PetModel result = petService.updatePetById(petId, updatedPet);

        assertEquals("Rex Updated", result.getName());
        assertEquals("Grande", result.getSize());
        assertEquals("Golden Retriever", result.getBreed());
        assertEquals("Longo", result.getCoat());
        assertEquals(6, result.getAge());
        assertEquals("M", result.getSex());
        assertNotNull(result.getLastUpdate());
        verify(petRepository).save(existingPet);
    }

    /**
     * * <p><b>2. Pet não encontrado:</b>
     *  * Garante que, ao buscar um pet que não existe, o método lance a exceção
     *  * {@code EntityNotFoundException} com a mensagem "Pet não encontrado".
     *  * Também verifica que a operação de salvar não é executada.</p>
     *  */
    @Test
    void testUpdatePetById_PetNotFound() {
        Integer petId = 999;
        PetModel updatedPet = new PetModel();

        when(petRepository.findById(petId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            petService.updatePetById(petId, updatedPet);
        });

        assertEquals("Pet não encontrado", exception.getMessage());
        verify(petRepository, never()).save(any());
    }

    /**
     * Testa o método {@link PetService#deletePetById(Integer)}.
     *
     * <p><b>1. Exclusão bem-sucedida:</b>
     * Verifica se o método chama corretamente o repositório para deletar um pet existente pelo ID.</p>
     */

    @Test
    void testDeletePetById_Success() {
        Integer petId = 1;

        when(petRepository.existsById(petId)).thenReturn(true);

        petService.deletePetById(petId);

        verify(petRepository).deleteById(petId);
    }

     /**
     *  * <p><b>2. Pet não encontrado:</b>
     *      * Verifica se o método lança {@code EntityNotFoundException} ao tentar deletar um pet que não existe,
     *      * e que o método {@code deleteById} não seja chamado.</p>
     *
     *     */

    @Test
    void testDeletePetById_PetNotFound() {
        Integer petId = 999;

        when(petRepository.existsById(petId)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            petService.deletePetById(petId);
        });

        assertEquals("Pet não encontrado", exception.getMessage());
        verify(petRepository, never()).deleteById(any());
    }

    // Mock classes for demonstration

    static class PetService {
        private final PetRepository petRepository;

        public PetService(PetRepository petRepository) {
            this.petRepository = petRepository;
        }

        public PetModel updatePetById(Integer id, PetModel updatedPet) {
            PetModel pet = petRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

            pet.setName(updatedPet.getName());
            pet.setSize(updatedPet.getSize());
            pet.setSpecies(updatedPet.getSpecies());
            pet.setBreed(updatedPet.getBreed());
            pet.setCoat(updatedPet.getCoat());
            pet.setAge(updatedPet.getAge());
            pet.setSex(updatedPet.getSex());
            pet.setLastUpdate(LocalDateTime.now());

            petRepository.save(pet);

            return pet;
        }

        public void deletePetById(Integer id) {
            if (!petRepository.existsById(id)) {
                throw new EntityNotFoundException("Pet não encontrado");
            }
            petRepository.deleteById(id);
        }
    }

    interface PetRepository {
        Optional<PetModel> findById(Integer id);
        PetModel save(PetModel pet);
        boolean existsById(Integer id);
        void deleteById(Integer id);
    }

    static class PetModel {
        private Integer id;
        private String name;
        private String size;
        private String species;
        private String breed;
        private String coat;
        private Integer age;
        private String sex;
        private LocalDateTime lastUpdate;

        // getters e setters

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getSize() { return size; }
        public void setSize(String size) { this.size = size; }

        public String getSpecies() { return species; }
        public void setSpecies(String species) { this.species = species; }

        public String getBreed() { return breed; }
        public void setBreed(String breed) { this.breed = breed; }

        public String getCoat() { return coat; }
        public void setCoat(String coat) { this.coat = coat; }

        public Integer getAge() { return age; }
        public void setAge(Integer age) { this.age = age; }

        public String getSex() { return sex; }
        public void setSex(String sex) { this.sex = sex; }

        public LocalDateTime getLastUpdate() { return lastUpdate; }
        public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
    }


}
