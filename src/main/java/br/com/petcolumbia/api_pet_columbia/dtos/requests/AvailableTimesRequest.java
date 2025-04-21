package br.com.petcolumbia.api_pet_columbia.dtos.requests;

import br.com.petcolumbia.api_pet_columbia.domain.entities.ServiceModel;

import java.time.LocalDate;
import java.util.List;

public class AvailableTimesRequest {
    private LocalDate date;
    private List<ServiceModel> services;

    public LocalDate getDate() {
        return date;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }
}