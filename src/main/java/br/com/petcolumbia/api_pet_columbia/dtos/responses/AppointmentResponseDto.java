package br.com.petcolumbia.api_pet_columbia.dtos.responses;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;

import java.time.LocalDateTime;

public class AppointmentResponseDto {
    private Integer id;
    private PetModel pet;
    private EmployeeModel employee;
    private Double totalPrice;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public AppointmentResponseDto(AppointmentModel appointment) {
    }

    public AppointmentResponseDto(Integer id, EmployeeModel employee, PetModel pet, LocalDateTime endDateTime, LocalDateTime startDateTime, Double totalPrice) {
        this.id = id;
        this.employee = employee;
        this.pet = pet;
        this.endDateTime = endDateTime;
        this.startDateTime = startDateTime;
        this.totalPrice = totalPrice;
    }
}
