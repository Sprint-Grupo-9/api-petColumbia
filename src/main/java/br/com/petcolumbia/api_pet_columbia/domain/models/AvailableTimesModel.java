package br.com.petcolumbia.api_pet_columbia.domain.models;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;

import java.time.LocalTime;
import java.util.List;

public class AvailableTimesModel {
    private EmployeeModel employee;
    private List<LocalTime> times;
    private String servicesNames;
    private Double servicePrice;

    public AvailableTimesModel(EmployeeModel employee, List<LocalTime> times, String servicesNames, Double servicePrice) {
        this.employee = employee;
        this.times = times;
        this.servicesNames = servicesNames;
        this.servicePrice = servicePrice;
    }
}
