package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.PriceAndTimeModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.PetResponseDto;
import br.com.petcolumbia.api_pet_columbia.repositories.IPriceAndTimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAndTimeService {

    private final IPriceAndTimeRepository priceAndTimeRepository;

    public PriceAndTimeService(IPriceAndTimeRepository priceAndTimeRepository) {
        this.priceAndTimeRepository = priceAndTimeRepository;
    }

    public PriceAndTimeModel timeAndPriceOfService(Integer serviceId, String petSize, String petCoat){
        return priceAndTimeRepository.findByIdAndPetSizeAndPetCoat(serviceId, petSize, petCoat);
    }

    public Double calculateTotalPrice(List<Integer> servicesIds, PetResponseDto pet) {
        return servicesIds.stream()
                .mapToDouble(serviceId ->
                        timeAndPriceOfService(serviceId, pet.getSize(), pet.getCoat())
                        .getPrice())
                .sum();
    }

    public Integer calculateTotalDuration(List<Integer> servicesIds, PetResponseDto pet) {
        return servicesIds.stream()
                .mapToInt(serviceId ->
                        timeAndPriceOfService(serviceId, pet.getSize(), pet.getCoat())
                        .getTime())
                .sum();
    }

}
