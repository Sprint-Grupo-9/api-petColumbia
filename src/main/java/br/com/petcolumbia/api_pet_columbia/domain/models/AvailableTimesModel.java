package br.com.petcolumbia.api_pet_columbia.domain.models;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.EmployeeResponse;

import java.time.LocalTime;
import java.util.List;

public class AvailableTimesModel {
    private EmployeeResponse employee;
    private List<LocalTime> times;
    private Integer durationTime;
    private String servicesNames;
    private Double servicePrice;

    public AvailableTimesModel(EmployeeResponse employee, List<LocalTime> times, Integer durationTime, String servicesNames, Double servicePrice) {
        this.employee = employee;
        this.times = times;
        this.durationTime = durationTime;
        this.servicesNames = servicesNames;
        this.servicePrice = servicePrice;
    }
}
