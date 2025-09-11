package br.com.petcolumbia.api_pet_columbia.core.adapter;

import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;

public interface OwnerGateway {
    Owner create(Owner owner);
    Owner getOwnerById(Integer id);
    void deleteOwnerById(Integer id);
    Owner updateOwnerById(Integer id, Owner owner);
    Owner updatePasswordById(Integer id, Owner owner);
    boolean isDuplicateFields(String email, String cpf, String phoneNumber, Integer id);
    Owner authenticate(Owner command);
}
