package br.com.petcolumbia.api_pet_columbia.dtos.requests;

import br.com.petcolumbia.api_pet_columbia.domain.entities.ServiceModel;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentCreateDto {
    @NotNull(message = "O Id do Pet é obrigatório")
    private Integer petId;

    @NotNull(message = "O Id do Funcionário é obrigatório")
    private Integer employee_id;

    @NotEmpty(message = "Os serviços são obrigatórios")
    private List<ServiceModel> services;

    @NotNull(message = "O preço é obrigatório")
    private Double totalPrice;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDateTime startDateTime;

    @NotNull(message = "A duração é obrigatória")
    private Integer durationMinutes;

    public Integer getPetId() {
        return petId;
    }

    public Integer getEmployee_id() {
        return employee_id;
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

}
