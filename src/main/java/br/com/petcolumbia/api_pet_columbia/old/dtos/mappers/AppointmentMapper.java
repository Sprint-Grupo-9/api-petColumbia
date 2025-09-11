package br.com.petcolumbia.api_pet_columbia.old.dtos.mappers;

import br.com.petcolumbia.api_pet_columbia.old.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.old.dtos.requests.appointmentDtos.AppointmentCreateDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.requests.appointmentDtos.AppointmentUpdateDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.appointmentDtos.AppointmentCardInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.appointmentDtos.AppointmentInfoResponseDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.appointmentDtos.AppointmentResponseDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.dashboard.LastAppointmentsListDto;
import br.com.petcolumbia.api_pet_columbia.old.dtos.responses.dashboard.LastPetAndOwnerAppointmentsResponseDto;
import br.com.petcolumbia.api_pet_columbia.old.services.EmployeeService;
import br.com.petcolumbia.api_pet_columbia.old.services.PetService;

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

        appointment.setObservations(dto.getObservations());
        appointment.setTaxiService(dto.getTaxiService());
        appointment.setServices(dto.getServicesNames());
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

        appointment.setObservations(dto.getObservations());
        appointment.setTaxiService(dto.getTaxiService());
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
        responseDto.setObservations(entity.getObservations());
        responseDto.setTaxiService(entity.getTaxiService());
        responseDto.setStartDateTime(entity.getStartDateTime());
        responseDto.setEndDateTime(entity.getEndDateTime());

        return responseDto;
    }

    public static List<AppointmentResponseDto> entitiesToResponses(List<AppointmentModel> appointments){
        List<AppointmentResponseDto> response = new ArrayList<>();

        for(AppointmentModel appointment: appointments){
            response.add(AppointmentMapper.entityToResponse(appointment));
        }

        return response;
    }

    public static AppointmentInfoResponseDto toInfoResponse (AppointmentModel entity, LastAppointmentsListDto lastPetsAppointments, LastAppointmentsListDto lastOwnerAppointments ) {
        AppointmentInfoResponseDto responseDto = new AppointmentInfoResponseDto();

        responseDto.setId(entity.getId());
        responseDto.setPet(PetMapper.entityToInfoResponse(entity.getPet()));
        responseDto.setEmployee(EmployeeMapper.entityToResponse(entity.getEmployee()));
        responseDto.setServices(entity.getServices());
        responseDto.setTotalPrice(entity.getTotalPrice());
        responseDto.setObservations(entity.getObservations());
        responseDto.setTaxiService(entity.getTaxiService());
        responseDto.setStartDateTime(entity.getStartDateTime());
        responseDto.setEndDateTime(entity.getEndDateTime());
        responseDto.setLastPetAppointments(lastPetsAppointments);
        responseDto.setLastOwnerAppointments(lastOwnerAppointments);

        return responseDto;
    }

    public static List<AppointmentInfoResponseDto> toInfoResponses (List<AppointmentModel> entities, LastPetAndOwnerAppointmentsResponseDto lastAppointments) {
        List<AppointmentInfoResponseDto> responseDtos = new ArrayList<>();
        List<LastAppointmentsListDto> lastPetAppointments = lastAppointments.getLastPetAppointments();
        List<LastAppointmentsListDto> lastOwnerAppointments = lastAppointments.getLastOwnerAppointments();

        for (int i = 0; i < entities.size(); i++){
            responseDtos.add(toInfoResponse(entities.get(i), lastPetAppointments.get(i), lastOwnerAppointments.get(i)));
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
        responseDto.setTaxiService(entity.getTaxiService());

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
