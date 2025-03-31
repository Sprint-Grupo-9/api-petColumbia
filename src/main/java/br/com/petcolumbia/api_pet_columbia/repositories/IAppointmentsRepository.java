package br.com.petcolumbia.api_pet_columbia.repositories;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentsRepository extends JpaRepository<AppointmentModel, Integer> {

//    @Query("SELECT p.nome FROM Produto p WHERE p.preco > :precoMinimo")
//    List<String> findNomesDeProdutosComPrecoMaiorQue(@Param("precoMinimo") BigDecimal precoMinimo);
}
