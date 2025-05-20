package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.ServicePriceAndDurationModel;
import br.com.petcolumbia.api_pet_columbia.repositories.IServicePriceAndDurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAndDurationService {

    private final IServicePriceAndDurationRepository priceAndDurationRepository;

    public PriceAndDurationService(IServicePriceAndDurationRepository priceAndDurationRepository) {
        this.priceAndDurationRepository = priceAndDurationRepository;
    }

    public ServicePriceAndDurationModel durationAndPriceOfService(Integer serviceId, String petSize, String petCoat){
        return priceAndDurationRepository.findByIdAndPetSizeAndPetCoat(serviceId, petSize, petCoat);
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
