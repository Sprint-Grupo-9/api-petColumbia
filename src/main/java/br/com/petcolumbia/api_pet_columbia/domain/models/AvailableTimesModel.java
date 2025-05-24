package br.com.petcolumbia.api_pet_columbia.domain.models;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.employeeDtos.EmployeeResponseDto;

import java.time.LocalTime;
import java.util.List;

public class AvailableTimesModel {
    private EmployeeResponseDto employee;
    private List<LocalTime> times;
    private Integer durationTime;
    private String servicesNames;
    private Double servicePrice;

    public AvailableTimesModel(EmployeeResponseDto employee, List<LocalTime> times, Integer durationTime, String servicesNames, Double servicePrice) {
        this.employee = employee;
        this.times = times;
        this.durationTime = durationTime;
        this.servicesNames = servicesNames;
        this.servicePrice = servicePrice;
    }

    public EmployeeResponseDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeResponseDto employee) {
        this.employee = employee;
    }

    public List<LocalTime> getTimes() {
        return times;
    }

    public void setTimes(List<LocalTime> times) {
        this.times = times;
    }

    public Integer getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Integer durationTime) {
        this.durationTime = durationTime;
    }

    public String getServicesNames() {
        return servicesNames;
    }

    public void setServicesNames(String servicesNames) {
        this.servicesNames = servicesNames;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }
}
