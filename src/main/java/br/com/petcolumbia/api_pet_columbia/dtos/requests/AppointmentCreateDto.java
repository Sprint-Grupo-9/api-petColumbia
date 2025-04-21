package br.com.petcolumbia.api_pet_columbia.dtos.requests;

import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.ServiceModel;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.PetResponseDto;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentCreateDto {
    @NotNull(message = "O Pet é obrigatório")
    private Integer petId;

    @NotNull(message = "O Funcionario é obrigatório")
    private Integer employee_id;

    @NotEmpty(message = "O serviço é obrigatório")
    private List<ServiceModel> services;

    @NotNull(message = "O preço é obrigatório")
    private Double totalPrice;

    @NotNull(message = "O data de inicio é obrigatória")
    private LocalDateTime startDateTime;

    @NotNull(message = "A duração é obrigatória")
    private Integer durationMinutes;

    public Integer getPet() {
        return petId;
    }

    public void setPet(Integer petId) {
        this.petId = petId;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
