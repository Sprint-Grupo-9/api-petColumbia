package br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.jpa.jpa_adapter;

import br.com.petcolumbia.api_pet_columbia.core.adapter.OwnerGateway;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.Owner;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject.Adress;
import br.com.petcolumbia.api_pet_columbia.core.domain.owner.valueobject.PersonalInfo;
import br.com.petcolumbia.api_pet_columbia.infrastructure.mappers.entity_mapper.PetEntityMapper;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.entity.OwnerEntity;
import br.com.petcolumbia.api_pet_columbia.infrastructure.persistence.jpa.repository.OwnerJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerJpaAdapter implements OwnerGateway {

    private final OwnerJpaRepository repository;

    public OwnerJpaAdapter(OwnerJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Owner create(Owner owner) {
        return null;
    }

    @Override
    public Owner getOwnerById(Integer id) {
        OwnerEntity owner = repository.findById(id).orElse(null);

        if (owner != null) {
            return new Owner(
                    owner.getId(),
                    owner.getName(),
                    new PersonalInfo(owner.getCpf(), owner.getPhoneNumber()),
                    owner.getEmail(),
                    owner.getPassword(),
                    new Adress(owner.getCep(), owner.getNeighborhood(), owner.getStreet(), owner.getNumber(), owner.getComplement()),
                    owner.getAdm(),
                    PetEntityMapper.toDomainList(owner.getPets())
            );
        }

        return null;
    }

    @Override
    public void deleteOwnerById(Integer id) {
    }

    @Override
    public Owner updateOwnerById(Integer id, Owner owner) {
        return null;
    }

    @Override
    public Owner updatePasswordById(Integer id, Owner owner) {
        return null;
    }

    @Override
    public boolean isDuplicateFields(String email, String cpf, String phoneNumber, Integer id) {
        if (id == null)  //Create
            return repository.existsByEmail(email)
                    || repository.existsByCpf(cpf)
                    || repository.existsByPhoneNumber(phoneNumber);

        //Update
        return repository.existsByEmailAndIdNot(email, id)
                || repository.existsByCpfAndIdNot(cpf, id)
                || repository.existsByPhoneNumberAndIdNot(phoneNumber, id);
    }

    @Override
    public Owner authenticate(Owner command) {
        return null;
    }
}
