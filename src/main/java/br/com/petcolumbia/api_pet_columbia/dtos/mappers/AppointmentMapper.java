package br.com.petcolumbia.api_pet_columbia.dtos.mappers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.appointmentDtos.AppointmentCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.appointmentDtos.AppointmentUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos.AppointmentCardInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos.AppointmentInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.appointmentDtos.AppointmentResponseDto;
import br.com.petcolumbia.api_pet_columbia.services.EmployeeService;
import br.com.petcolumbia.api_pet_columbia.services.PetService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentMapper {

    private final PetService petService;

    private final EmployeeService employeeService;

    public AppointmentMapper(PetService petService, EmployeeService employeeService) {
        this.petService = petService;
        this.employeeService = employeeService;
    }

    public static AppointmentModel createDtoToEntity(AppointmentCreateDto dto) {
        AppointmentModel appointment = new AppointmentModel();

        appointment.setServices(dto.getServices().toString());
        appointment.setTotalPrice(dto.getTotalPrice());
        appointment.setStartDateTime(dto.getStartDateTime());
        appointment.setEndDateTime(dto.getStartDateTime().plusMinutes(dto.getDurationMinutes()));
        appointment.setFinished(false);
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setLastUpdate(LocalDateTime.now());

        return appointment;
    }

    public static AppointmentModel updateDtoToEntity(AppointmentUpdateDto dto) {
        AppointmentModel appointment = new AppointmentModel();

        appointment.setServices(dto.getServices().toString());
        appointment.setTotalPrice(dto.getTotalPrice());
        appointment.setStartDateTime(dto.getStartDateTime());
        appointment.setEndDateTime(dto.getStartDateTime().plusMinutes(dto.getDurationMinutes()));
        appointment.setFinished(false);
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setLastUpdate(LocalDateTime.now());

        return appointment;
    }

    public static AppointmentResponseDto entityToResponse (AppointmentModel entity) {
        AppointmentResponseDto responseDto = new AppointmentResponseDto();

        responseDto.setId(entity.getId());
        responseDto.setPet(PetMapper.entityToResponse(entity.getPet()));
        responseDto.setEmployee(EmployeeMapper.entityToResponse(entity.getEmployee()));
        responseDto.setServices(entity.getServices());
        responseDto.setTotalPrice(entity.getTotalPrice());
        responseDto.setStartDateTime(entity.getStartDateTime());
        responseDto.setEndDateTime(entity.getEndDateTime());

        return responseDto;
    }

    public static AppointmentInfoResponseDto entityToInfoResponse (AppointmentModel entity) {
        AppointmentInfoResponseDto responseDto = new AppointmentInfoResponseDto();

        responseDto.setId(entity.getId());
        responseDto.setPet(PetMapper.entityToInfoResponse(entity.getPet()));
        responseDto.setEmployee(EmployeeMapper.entityToResponse(entity.getEmployee()));
        responseDto.setServices(entity.getServices());
        responseDto.setTotalPrice(entity.getTotalPrice());
        responseDto.setStartDateTime(entity.getStartDateTime());
        responseDto.setEndDateTime(entity.getEndDateTime());

        return responseDto;
    }

    public static List<AppointmentInfoResponseDto> entitiesToInfoResponses (List<AppointmentModel> entities) {
        List<AppointmentInfoResponseDto> responseDtos = new ArrayList<>();

        for (AppointmentModel entity : entities){
            responseDtos.add(entityToInfoResponse(entity));
        }

        return responseDtos;
    }

    public static AppointmentCardInfoResponseDto entityToCardnfoResponse (AppointmentModel entity) {
        AppointmentCardInfoResponseDto responseDto = new AppointmentCardInfoResponseDto();

        responseDto.setStartTime(entity.getStartDateTime().toLocalTime());
        responseDto.setEndTime(entity.getEndDateTime().toLocalTime());
        responseDto.setOwnerName(entity.getPet().getOwner().getName());
        responseDto.setPetName(entity.getPet().getName());
        responseDto.setPetBreed(entity.getPet().getBreed());
        responseDto.setServicesNames(entity.getServices());
        responseDto.setPrice(entity.getTotalPrice());

        return  responseDto;
    }

    public static List<AppointmentCardInfoResponseDto> entitiesToCardInfoResponses(List<AppointmentModel> entities) {
        List<AppointmentCardInfoResponseDto> responseDtos = new ArrayList<>();

        for (AppointmentModel entity : entities){
            responseDtos.add(entityToCardnfoResponse(entity));
        }

        return responseDtos;
    }
}
