package br.com.petcolumbia.api_pet_columbia.old.dtos.responses.dashboard;

import java.util.List;

public class LastPetAndOwnerAppointmentsResponseDto {
    private List<LastAppointmentsListDto> lastPetAppointments;
    private List<LastAppointmentsListDto> lastOwnerAppointments;

    public LastPetAndOwnerAppointmentsResponseDto() {
    }

    public LastPetAndOwnerAppointmentsResponseDto(List<LastAppointmentsListDto> lastPetAppointments, List<LastAppointmentsListDto> lastOwnerAppointments) {
        this.lastPetAppointments = lastPetAppointments;
        this.lastOwnerAppointments = lastOwnerAppointments;
    }

    public List<LastAppointmentsListDto> getLastPetAppointments() {
        return lastPetAppointments;
    }

    public void setLastPetAppointments(List<LastAppointmentsListDto> lastPetAppointments) {
        this.lastPetAppointments = lastPetAppointments;
    }

    public List<LastAppointmentsListDto> getLastOwnerAppointments() {
        return lastOwnerAppointments;
    }

    public void setLastOwnerAppointments(List<LastAppointmentsListDto> lastOwnerAppointments) {
        this.lastOwnerAppointments = lastOwnerAppointments;
    }
}
