package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.employee.EmployeeResponseDto;

import java.time.LocalTime;
import java.util.List;

public class AvailableTimesResponseDto {
    private EmployeeResponseDto employee;
    private List<LocalTime> times;
    private Integer durationTime;
    private String petOfferingNames;
    private Double servicePrice;

    public AvailableTimesResponseDto() {
    }

    public AvailableTimesResponseDto(EmployeeResponseDto employee, List<LocalTime> times, Integer durationTime, String petOfferingNames, Double servicePrice) {
        this.employee = employee;
        this.times = times;
        this.durationTime = durationTime;
        this.petOfferingNames = petOfferingNames;
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

    public String getPetOfferingNames() {
        return petOfferingNames;
    }

    public void setPetOfferingNames(String petOfferingNames) {
        this.petOfferingNames = petOfferingNames;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }
}

