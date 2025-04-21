package br.com.petcolumbia.api_pet_columbia.dtos.mappers;

import br.com.petcolumbia.api_pet_columbia.domain.entities.AppointmentModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.EmployeeModel;
import br.com.petcolumbia.api_pet_columbia.domain.entities.PetModel;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.AppointmentCreateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.requests.AppointmentUpdateDto;
import br.com.petcolumbia.api_pet_columbia.dtos.responses.AppointmentResponseDto;
import br.com.petcolumbia.api_pet_columbia.exceptions.EntityNotFoundException;
import br.com.petcolumbia.api_pet_columbia.services.EmployeeService;
import br.com.petcolumbia.api_pet_columbia.services.PetService;

import java.time.LocalDateTime;

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
}
