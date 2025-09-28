package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common;

/**
 * Factory utility for creating common mapping contexts with predefined strategies.
 * This class provides convenient methods for creating mapping contexts for different scenarios.
 */
public final class MappingContextFactory {

    private MappingContextFactory() {
        // Utility class - prevent instantiation
    }

    /**
     * Creates a context for mapping owners with their pets, but prevents deep recursion
     */
    public static MappingContext ownerWithPets() {
        return new MappingContext(new MappingStrategy.OwnerWithPetsMapping());
    }

    /**
     * Creates a context for mapping pets with their owners, but without appointments
     */
    public static MappingContext petWithOwner() {
        return new MappingContext(new MappingStrategy.PetWithOwnerMapping());
    }

    /**
     * Creates a context for mapping appointments with full context (pet + owner + employee)
     */
    public static MappingContext appointmentFull() {
        return new MappingContext(new MappingStrategy.AppointmentFullMapping());
    }

    /**
     * Creates a context for mapping employees with their procedures, but without appointments
     */
    public static MappingContext employeeWithProcedures() {
        return new MappingContext(new MappingStrategy.EmployeeWithProceduresMapping());
    }

    /**
     * Creates a shallow context that doesn't map any relationships (prevents all recursion)
     */
    public static MappingContext shallow() {
        return new MappingContext(new MappingStrategy.ShallowMapping());
    }

    /**
     * Creates a full context that maps all relationships (use with extreme caution)
     * Note: This should only be used in very specific scenarios and with small datasets
     */
    public static MappingContext full() {
        return new MappingContext(new MappingStrategy.FullMapping());
    }

    /**
     * Creates a custom context with a specific strategy
     */
    public static MappingContext custom(MappingStrategy strategy) {
        return new MappingContext(strategy);
    }
}
