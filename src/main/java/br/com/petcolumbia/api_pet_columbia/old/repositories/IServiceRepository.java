package br.com.petcolumbia.api_pet_columbia.old.repositories;

import br.com.petcolumbia.api_pet_columbia.old.domain.entities.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceRepository extends JpaRepository<ServiceModel, Integer> {

}
