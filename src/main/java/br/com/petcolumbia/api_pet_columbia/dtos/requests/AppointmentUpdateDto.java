package br.com.petcolumbia.api_pet_columbia.dtos.requests;

import br.com.petcolumbia.api_pet_columbia.domain.entities.ServiceModel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentUpdateDto {
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

    public Integer getPetId() {
        return petId;
    }

    public void setPetId (Integer petId) {
        this.petId = petId;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices (List<ServiceModel> services) {
        this.services = services;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
