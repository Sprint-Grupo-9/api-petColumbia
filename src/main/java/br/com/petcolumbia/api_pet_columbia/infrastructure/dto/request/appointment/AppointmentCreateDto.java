package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.request.appointment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AppointmentCreateDto {
    @NotNull(message = "O Id do Pet é obrigatório")
    private Integer petId;

    @NotNull(message = "O Id do Funcionário é obrigatório")
    private Integer employee_id;

    @NotEmpty(message = "Os serviços são obrigatórios")
    private String proceduresNames;

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

    public @NotNull(message = "O Id do Pet é obrigatório") Integer getPetId() {
        return petId;
    }

    public void setPetId(@NotNull(message = "O Id do Pet é obrigatório") Integer petId) {
        this.petId = petId;
    }

    public @NotNull(message = "O Id do Funcionário é obrigatório") Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(@NotNull(message = "O Id do Funcionário é obrigatório") Integer employee_id) {
        this.employee_id = employee_id;
    }

    public @NotEmpty(message = "Os serviços são obrigatórios") String getProceduresNames() {
        return proceduresNames;
    }

    public void setProceduresNames(@NotEmpty(message = "Os serviços são obrigatórios") String proceduresNames) {
        this.proceduresNames = proceduresNames;
    }

    public @NotNull(message = "O preço é obrigatório") Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(@NotNull(message = "O preço é obrigatório") Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public @NotNull(message = "A data de início é obrigatória") LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(@NotNull(message = "A data de início é obrigatória") LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public @NotNull(message = "A duração é obrigatória") Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(@NotNull(message = "A duração é obrigatória") Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
