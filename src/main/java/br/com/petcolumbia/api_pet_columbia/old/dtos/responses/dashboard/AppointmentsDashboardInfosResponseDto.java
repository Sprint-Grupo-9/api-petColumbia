package br.com.petcolumbia.api_pet_columbia.old.dtos.responses.dashboard;

import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.appointmentDtos.AppointmentCardInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.appointmentDtos.AppointmentInfoResponseDto;

import java.util.List;

public class AppointmentsDashboardInfosResponseDto {
    private List<AppointmentCardInfoResponseDto> cardResponses;
    private List<AppointmentInfoResponseDto> infoResponses;

    public List<AppointmentCardInfoResponseDto> getCardResponses() {
        return cardResponses;
    }

    public void setCardResponses(List<AppointmentCardInfoResponseDto> cardResponses) {
        this.cardResponses = cardResponses;
    }

    public List<AppointmentInfoResponseDto> getInfoResponses() {
        return infoResponses;
    }

    public void setInfoResponses(List<AppointmentInfoResponseDto> infoResponses) {
        this.infoResponses = infoResponses;
    }
}
