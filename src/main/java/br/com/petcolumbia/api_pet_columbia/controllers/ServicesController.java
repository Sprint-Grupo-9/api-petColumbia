package br.com.petcolumbia.api_pet_columbia.controllers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.ServiceModel;
import br.com.petcolumbia.api_pet_columbia.dtos.mappers.ServiceMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.ServiceResponseDto;
import br.com.petcolumbia.api_pet_columbia.services.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @GetMapping()
    @Operation(summary = "Busca todos os serviços")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ServiceResponseDto>> getServices(){
        List<ServiceModel> servicesList = serviceService.listServices();
        return ResponseEntity.status(200).body(ServiceMapper.entityToResponse(servicesList));
    }
}
