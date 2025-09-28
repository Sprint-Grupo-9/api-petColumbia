package br.com.petcolumbia.api_pet_columbia.old.services;

import br.com.petcolumbia.api_pet_columbia.old.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.old.domain.entities.ServicePriceAndDurationModel;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.serviceDtos.ServicesWithPriceResponseDto;
import br.com.petcolumbia.api_pet_columbia.old.exceptions.EntityConflictException;
import br.com.petcolumbia.api_pet_columbia.old.exceptions.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.old.repositories.IPetRepository;
import br.com.petcolumbia.api_pet_columbia.old.repositories.IServicePriceAndDurationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ServicePriceAndDurationService {

    private final IServicePriceAndDurationRepository priceAndDurationRepository;
    private final ServiceService serviceService;
    private final IPetRepository petRepository;;

    public ServicePriceAndDurationService(IServicePriceAndDurationRepository priceAndDurationRepository, ServiceService serviceService, IPetRepository petRepository) {
        this.priceAndDurationRepository = priceAndDurationRepository;
        this.serviceService = serviceService;
        this.petRepository = petRepository;
    }

    public ServicePriceAndDurationModel durationAndPriceOfService(Integer serviceId, String petSize, String petCoat){
        return priceAndDurationRepository.findByIdAndPetSizeAndPetCoat(serviceId, petSize, petCoat);
    }

    public List<ServicesWithPriceResponseDto> servicesPricesByPetAndServicesIds(Integer petId, List<Integer> servicesIds){
        Optional<PetModel> optionalPet = petRepository.findById(petId);

        if(optionalPet.isEmpty())
            throw new EntityNotFoundException("Pet não encontrado");

        PetModel pet = optionalPet.get();

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
