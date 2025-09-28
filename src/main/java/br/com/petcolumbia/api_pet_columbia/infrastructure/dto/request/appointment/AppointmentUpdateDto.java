package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.appointment;

import br.com.petcolumbia.api_pet_columbia.core.domain.model.procedure.Procedure;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentUpdateDto {
    @NotNull(message = "O Id do Pet é obrigatório")
    private Integer petId;

    @NotNull(message = "O Id do Funcionário é obrigatório")
    private Integer employee_id;

    @NotEmpty(message = "Os serviços são obrigatórios")
    private List<Procedure> procedures;

    @NotNull(message = "O preço é obrigatório")
    private Double totalPrice;

    private String observations;

    private Boolean taxiService;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDateTime startDateTime;

    @NotNull(message = "A duração é obrigatória")
    private Integer durationMinutes;

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Boolean getTaxiService() {
        return taxiService;
    }

    public void setTaxiService(Boolean taxiService) {
        this.taxiService = taxiService;
    }

    public Integer getPetId() {
        return petId;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
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
