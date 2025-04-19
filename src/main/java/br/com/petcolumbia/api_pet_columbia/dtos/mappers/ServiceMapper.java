package br.com.petcolumbia.api_pet_columbia.dtos.mappers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.ServiceModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.ServiceResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ServiceMapper {

    public static List<ServiceResponseDto> entityToResponse(List<ServiceModel> services) {
        List<ServiceResponseDto> responses = new ArrayList<>();

        for (ServiceModel service : services) {
            ServiceResponseDto serviceResponseDto = new ServiceResponseDto();

            serviceResponseDto.setId(service.getId());
            serviceResponseDto.setName(service.getName());
            serviceResponseDto.setDescription(service.getDescription());

            responses.add(serviceResponseDto);
        }

        return responses;
    }
}
