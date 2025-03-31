package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.models.AvailableTimes;
import br.com.petcolumbia.api_pet_columbia.models.PetModel;
import br.com.petcolumbia.api_pet_columbia.models.ServiceModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {


    @GetMapping()
    public void pegarHorarios(@RequestParam LocalDate date, PetModel pet, ServiceModel service){

        List<AvailableTimes> availableTimes = new ArrayList<>();

        //verificar se o dia é valido na tabela de businessTime
        //

        /*
        exemplo pegar funcionarios que fazem serviço x
        select e.* from employee e
        join employees_has_services es on e.id = es.employee_id
        join service s on s.id = es.services_id
        where s.id = {serviço x};
         */
        //pegar o serviço, (query personalizada) e ver com base no pet quais funcionarios fazem
        //pegar os agendamentos desses funcionarios
        //pegar os horários disponiveis de cada funcionario

    }

}
