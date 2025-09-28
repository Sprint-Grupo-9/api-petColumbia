package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common;

/**
 * Strategy interface for defining which relationships should be included during mapping
 */
public interface MappingStrategy {

    boolean shouldInclude(RelationType relationType);

    /**
     * Strategy that includes all relationships (use with caution)
     */
    class FullMapping implements MappingStrategy {
        @Override
        public boolean shouldInclude(RelationType relationType) {
            return true;
        }
    }

    /**
     * Strategy that excludes nested relationships to prevent recursion
     */
    class ShallowMapping implements MappingStrategy {
        @Override
        public boolean shouldInclude(RelationType relationType) {
            return false;
        }
    }

    /**
     * Strategy for mapping owners with their pets but without deep nesting
     */
    class OwnerWithPetsMapping implements MappingStrategy {
        @Override
        public boolean shouldInclude(RelationType relationType) {
            return switch (relationType) {
                case OWNER_PETS -> true;
                case PET_OWNER -> false; // Prevent circular reference
                case PET_APPOINTMENTS -> false;
                default -> false;
            };
        }
    }

    /**
     * Strategy for mapping pets with owner but without appointments
     */
    class PetWithOwnerMapping implements MappingStrategy {
        @Override
        public boolean shouldInclude(RelationType relationType) {
            return switch (relationType) {
                case PET_OWNER -> true;
                case OWNER_PETS -> false; // Prevent circular reference
                case PET_APPOINTMENTS -> false;
                default -> false;
            };
        }
    }

    /**
     * Strategy for mapping appointments with full context
     */
    class AppointmentFullMapping implements MappingStrategy {
        @Override
        public boolean shouldInclude(RelationType relationType) {
            return switch (relationType) {
                case APPOINTMENT_PET -> true;
                case APPOINTMENT_EMPLOYEE -> true;
                case PET_OWNER -> true;
                case PET_APPOINTMENTS -> false; // Prevent circular reference
                case EMPLOYEE_APPOINTMENTS -> false; // Prevent circular reference
                case EMPLOYEE_PROCEDURES -> true;
                default -> false;
            };
        }
    }

    /**
     * Strategy for mapping employees with procedures but without appointments
     */
    class EmployeeWithProceduresMapping implements MappingStrategy {
        @Override
        public boolean shouldInclude(RelationType relationType) {
            return switch (relationType) {
                case EMPLOYEE_PROCEDURES -> true;
                case PROCEDURE_EMPLOYEES -> false; // Prevent circular reference
                case EMPLOYEE_APPOINTMENTS -> false;
                default -> false;
            };
        }
    }
}
