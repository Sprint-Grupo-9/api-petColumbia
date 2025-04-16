package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.ServiceModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.ServiceResponse;
import br.com.petcolumbia.api_pet_columbia.services.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {

    private final ServiceService serviceService;

    public ServicesController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Operation(summary = "Busca todos os serviços")
    @GetMapping()
    public ResponseEntity<List<ServiceResponse>> getServices(){
        List<ServiceResponse> servicesList = serviceService.listServices();
        return ResponseEntity.status(200).body(servicesList);
    }
}
