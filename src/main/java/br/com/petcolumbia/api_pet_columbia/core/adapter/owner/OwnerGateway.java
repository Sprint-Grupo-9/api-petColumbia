package br.com.petcolumbia.api_pet_columbia.core.adapter.owner;

import br.com.petcolumbia.api_pet_columbia.core.application.command.owner.OwnerUpdateCommand;
import br.com.petcolumbia.api_pet_columbia.core.domain.model.owner.Owner;

public interface OwnerGateway {
    Owner create(Owner owner);
    Owner getOwnerById(Integer id);
    void deleteOwnerById(Integer id);
    Owner updateOwnerById(Integer id, OwnerUpdateCommand command);
    Owner getOwnerByEmail(String email);

    boolean existsById(Integer id);
}
