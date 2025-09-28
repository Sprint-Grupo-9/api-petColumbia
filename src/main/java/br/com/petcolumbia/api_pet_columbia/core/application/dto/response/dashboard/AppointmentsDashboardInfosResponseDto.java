package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard;

import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AppointmentCardInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.core.application.dto.response.appointment.AppointmentInfoResponseDto;

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
