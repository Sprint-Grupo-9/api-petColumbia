package br.com.petcolumbia.api_pet_columbia.dtos.requests.others;

import br.com.petcolumbia.api_pet_columbia.dtos.requests.serviceDtos.ServiceRequest;

import java.time.LocalDate;
import java.util.List;

public class AvailableTimesRequest {
    private LocalDate date;
    private List<ServiceRequest> services;
    private Boolean taxiService;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getTaxiService() {
        return taxiService;
    }

    public void setTaxiService(Boolean taxiService) {
        this.taxiService = taxiService;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<ServiceRequest> getServices() {
        return services;
    }

    public void setServices(List<ServiceRequest> services) {
        this.services = services;
    }
}