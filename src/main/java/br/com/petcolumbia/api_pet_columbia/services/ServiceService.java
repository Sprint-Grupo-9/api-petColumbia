package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.dtos.mappers.ServiceMapper;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.ServiceResponseDto;
import br.com.petcolumbia.api_pet_columbia.repositories.IServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    private final IServiceRepository serviceRepository;

    public ServiceService(IServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceResponseDto> listServices() {
        return ServiceMapper.entityToResponse(serviceRepository.findAll());
    }

    public String getServicesNames(List<ServiceResponseDto> services){
        return services.stream()
                .map(ServiceResponseDto::getName)
                .collect(Collectors.joining(", "));
    }

    public List<Integer> getServiceIds(List<ServiceResponseDto> services) {
        return services.stream()
                .map(ServiceResponseDto::getId)
                .collect(Collectors.toList());
    }


}
