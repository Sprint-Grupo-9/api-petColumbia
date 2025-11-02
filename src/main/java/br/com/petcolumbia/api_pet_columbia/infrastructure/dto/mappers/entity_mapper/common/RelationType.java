package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common;

/**
 * Defines the types of relationships that can be mapped
 */
public enum RelationType {
    OWNER_PETS,
    PET_OWNER,
    PET_APPOINTMENTS,
    APPOINTMENT_PET,
    APPOINTMENT_EMPLOYEE,
    EMPLOYEE_APPOINTMENTS,
    EMPLOYEE_PET_OFFERINGS,
    PET_OFFERING_EMPLOYEES,
    PET_OFFERING_PRICES_DURATIONS
}
