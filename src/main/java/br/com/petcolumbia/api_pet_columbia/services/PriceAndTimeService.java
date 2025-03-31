package br.com.petcolumbia.api_pet_columbia.services;

import br.com.petcolumbia.api_pet_columbia.domain.entities.PriceAndTimeModel;
import br.com.petcolumbia.api_pet_columbia.repositories.IPriceAndTimeRepository;
import org.springframework.stereotype.Service;

@Service
public class PriceAndTimeService {

    private final IPriceAndTimeRepository priceAndTimeRepository;

    public PriceAndTimeService(IPriceAndTimeRepository priceAndTimeRepository) {
        this.priceAndTimeRepository = priceAndTimeRepository;
    }

    public PriceAndTimeModel pegarServicoPrecoTempo(Integer serviceId, String petSize, String petCoat){
        return priceAndTimeRepository.findByIdAndPetSizeAndPetCoat(serviceId, petSize, petCoat);
    }
}
