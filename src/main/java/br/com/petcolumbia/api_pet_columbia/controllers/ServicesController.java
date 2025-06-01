package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.ServiceModel;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.ServiceMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.serviceDtos.ServiceResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.serviceDtos.ServicesWithPriceResponseDto;
import br.com.petcolumbia.api_pet_columbia.services.ServicePriceAndDurationService;
import br.com.petcolumbia.api_pet_columbia.services.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Dictionary;
import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {

    private final ServiceService serviceService;
    private final ServicePriceAndDurationService priceAndDurationService;

    public ServicesController(ServiceService serviceService, ServicePriceAndDurationService priceAndDurationService) {
        this.serviceService = serviceService;
        this.priceAndDurationService = priceAndDurationService;
    }

    @GetMapping()
    @Operation(summary = "Busca todos os serviços")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ServiceResponseDto>> getServices(){
        List<ServiceModel> servicesList = serviceService.listServices();
        return ResponseEntity.status(200).body(ServiceMapper.entitiesToResponses(servicesList));
    }

    @GetMapping("/price/{petId}")
    @Operation(summary = "Busca os preços de todos os serviços para um pet específico", description = "Recebe o id do pet e uma lista de ids dos serviços")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ServicesWithPriceResponseDto>> getServicesPricesOfPet(@PathVariable Integer petId, @RequestParam List<Integer> servicesIds) {
        List<ServicesWithPriceResponseDto> servicesList = priceAndDurationService.servicesPricesByPetAndServicesIds(petId, servicesIds);
        if (servicesList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(servicesList);
    }
}
