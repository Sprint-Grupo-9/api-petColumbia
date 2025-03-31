package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.PriceAndTimeModel;
import br.com.petcolumbia.api_pet_columbia.domain.models.AvailableTimesModel;
import br.com.petcolumbia.api_pet_columbia.services.EmployeeServiceAssociationService;
import br.com.petcolumbia.api_pet_columbia.services.PriceAndTimeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    private final EmployeeServiceAssociationService employeeServiceAssociation;
    private final PriceAndTimeService priceAndTimeService;

    public AppointmentsController(
            EmployeeServiceAssociationService employeeServiceAssociation,
            PriceAndTimeService priceAndTimeService) {
        this.employeeServiceAssociation = employeeServiceAssociation;
        this.priceAndTimeService = priceAndTimeService;
    }

    @GetMapping()
    public List<AvailableTimesModel> pegarHorarios(
            @RequestParam LocalDate date, @RequestBody PetModel pet, Integer serviceId){

        List<AvailableTimesModel> availableTimeModels = new ArrayList<>();

        List<EmployeeModel> employees = employeeServiceAssociation
                .listEmployeesServices(serviceId);

        PriceAndTimeModel service = priceAndTimeService.
                pegarServicoPrecoTempo(serviceId, pet.getSize(), pet.getCoat());

        for(EmployeeModel employee : employees){
            List<String> times = new ArrayList<>();

        }

        //pegar os agendamentos desses funcionarios
        //pegar os horários disponiveis de cada funcionario
        //retornar os funcionarios e seus horários disponiveis
        return null;
    }

}
