package br.com.petcolumbia.api_pet_columbia.old.dtos.mappers;

import br.com.petcolumbia.api_pet_columbia.old.domain.entities.ServiceModel;
import br.com.petcolumbia.api_pet_columbia.old.dtos.requests.serviceDtos.ServiceRequest;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.serviceDtos.ServiceResponseDto;

import java.util.ArrayList;
import java.util.List;

public class ServiceMapper {

    public static List<ServiceResponseDto> entitiesToResponses(List<ServiceModel> services) {
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

    public static ServiceModel requestToEntity(ServiceRequest request) {
        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setId(request.getId());
        serviceModel.setName(request.getName());
        serviceModel.setDescription(request.getDescription());
        return serviceModel;
    }

    public static List<ServiceModel> requestsToEntities(List<ServiceRequest> requests) {
        List<ServiceModel> services = new ArrayList<>();

        for (ServiceRequest request : requests) {
            ServiceModel serviceModel = requestToEntity(request);
            services.add(serviceModel);
        }

        return services;
    }
}
