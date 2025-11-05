package br.com.petcolumbia.api_pet_columbia.core.adapter.owner;

public interface OwnerValidationGateway {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPhoneNumber(String telefone);
    boolean existsByEmailAndIdNot(String email, Integer id);
    boolean existsByCpfAndIdNot(String cpf, Integer id);
    boolean existsByPhoneNumberAndIdNot(String telefone, Integer id);


    }