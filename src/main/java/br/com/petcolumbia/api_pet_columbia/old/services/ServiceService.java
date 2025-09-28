package br.com.petcolumbia.api_pet_columbia.old.services;

import br.com.petcolumbia.api_pet_columbia.old.domain.entities.ServiceModel;
import br.com.petcolumbia.api_pet_columbia.old.repositories.IServiceRepository;
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

    public String getServicesNamesByIds(List<Integer> servicesIds){
        return serviceRepository.findAllById(servicesIds)
                .stream()
                .map(ServiceModel::getName)
                .collect(Collectors.joining(", "));
    }

    public List<Integer> getServiceIds(List<ServiceModel> services) {
        return services.stream()
                .map(ServiceModel::getId)
                .collect(Collectors.toList());
    }


}
