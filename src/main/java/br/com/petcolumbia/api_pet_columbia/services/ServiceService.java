package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.ServiceModel;
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

    public List<ServiceModel> listServices() {
        return serviceRepository.findAll();
    }

    public String getServicesNames(List<ServiceModel> services){
        return services.stream()
                .map(ServiceModel::getName)
                .collect(Collectors.joining(", "));
    }

    public List<Integer> getServiceIds(List<ServiceModel> services) {
        return services.stream()
                .map(ServiceModel::getId)
                .collect(Collectors.toList());
    }


}
