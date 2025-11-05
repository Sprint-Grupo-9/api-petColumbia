package br.com.petcolumbia.api_pet_columbia.core.application.dto.response.dashboard;

import java.util.List;

public class LastAppointmentsListDto {
    private List<LastAppointmentsDto> dtoList;

    public LastAppointmentsListDto() {
    }

    public LastAppointmentsListDto(List<LastAppointmentsDto> dtoList) {
        this.dtoList = dtoList;
    }

    public List<LastAppointmentsDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<LastAppointmentsDto> dtoList) {
        this.dtoList = dtoList;
    }
}
