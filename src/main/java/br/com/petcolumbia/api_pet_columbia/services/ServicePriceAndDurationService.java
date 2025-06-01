package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.ServicePriceAndDurationModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.serviceDtos.ServicesWithPriceResponseDto;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.repositories.IServicePriceAndDurationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;

@Service
public class ServicePriceAndDurationService {

    private final IServicePriceAndDurationRepository priceAndDurationRepository;
    private final ServiceService serviceService;
    private final PetService petService;

    public ServicePriceAndDurationService(IServicePriceAndDurationRepository priceAndDurationRepository, ServiceService serviceService, PetService petService) {
        this.priceAndDurationRepository = priceAndDurationRepository;
        this.serviceService = serviceService;
        this.petService = petService;
    }

    public ServicePriceAndDurationModel durationAndPriceOfService(Integer serviceId, String petSize, String petCoat){
        return priceAndDurationRepository.findByIdAndPetSizeAndPetCoat(serviceId, petSize, petCoat);
    }

    public List<ServicesWithPriceResponseDto> servicesPricesByPetAndServicesIds(Integer petId, List<Integer> servicesIds){
        PetModel pet = petService.findPetById(petId);

        List<ServicesWithPriceResponseDto> response = new ArrayList<>();

        for(Integer serviceId: servicesIds){
            ServicePriceAndDurationModel servicePriceAndDuration = durationAndPriceOfService(serviceId, pet.getSize(), pet.getCoat());

            String serviceName = serviceService.getServicesNamesByIds(Collections.singletonList(serviceId));

            response.add(new ServicesWithPriceResponseDto(serviceId, serviceName, servicePriceAndDuration.getPrice()));
        }

        if (response.isEmpty())
            throw new EntityNotFoundException("Serviços não encontrados para o tipo do pet.");

        return response;
    }

    public Double calculateTotalPrice(List<Integer> servicesIds, PetModel pet) {
        return servicesIds.stream()
                .mapToDouble(serviceId ->
                        durationAndPriceOfService(serviceId, pet.getSize(), pet.getCoat())
                        .getPrice())
                .sum();
    }

    public Integer calculateTotalDuration(List<Integer> servicesIds, PetModel pet) {
        return servicesIds.stream()
                .mapToInt(serviceId ->
                        durationAndPriceOfService(serviceId, pet.getSize(), pet.getCoat())
                        .getDuration())
                .sum();
    }

}
