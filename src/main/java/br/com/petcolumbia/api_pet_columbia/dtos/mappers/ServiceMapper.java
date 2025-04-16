package br.com.petcolumbia.api_pet_columbia.dtos.mappers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.ServiceModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

public class ServiceMapper {

    public static List<ServiceResponse> entityToResponse(List<ServiceModel> services) {
        List<ServiceResponse> responses = new ArrayList<>();

        for (ServiceModel service : services) {
            ServiceResponse serviceResponse = new ServiceResponse();

            serviceResponse.setId(service.getId());
            serviceResponse.setName(service.getName());
            serviceResponse.setDescription(service.getDescription());

            responses.add(serviceResponse);
        }

        return responses;
    }
}
