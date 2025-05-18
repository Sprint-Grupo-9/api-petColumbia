package br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos;

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
