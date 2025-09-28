package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common;

/**
 * GUIA DE USO - SISTEMA DE MAPEAMENTO REFATORADO
 * ==============================================
 *
 * Este guia explica como usar o novo sistema de mapeamento que resolve os problemas de recursividade.
 *
 * PROBLEMAS RESOLVIDOS:
 * - Recursividade infinita entre Owner ↔ Pet ↔ Appointment ↔ Employee
 * - Sets estáticos thread-unsafe que causavam vazamentos de memória
 * - Falta de controle sobre profundidade de mapeamento
 * - Mistura de responsabilidades
 *
 * EXEMPLOS DE USO:
 *
 * 1. MAPEAMENTO BÁSICO (compatibilidade com código existente):
 *
 *    Owner owner = OwnerEntityMapper.of(ownerEntity);
 *    Pet pet = PetEntityMapper.toDomain(petEntity);
 *
 * 2. MAPEAMENTO COM CONTEXTO ESPECÍFICO:
 *
 *    // Para mapear owner com pets, mas sem appointments
 *    Owner owner = OwnerEntityMapper.toDomain(ownerEntity, MappingContextFactory.ownerWithPets());
 *
 *    // Para mapear appointment com contexto completo
 *    Appointment appointment = AppointmentEntityMapper.toDomain(entity, MappingContextFactory.appointmentFull());
 *
 *    // Para mapeamento shallow (sem relacionamentos)
 *    Pet pet = PetEntityMapper.toDomain(petEntity, MappingContextFactory.shallow());
 *
 * 3. MAPEAMENTO DE LISTAS:
 *
 *    List<Owner> owners = OwnerEntityMapper.toDomainList(entities, MappingContextFactory.ownerWithPets());
 *
 * 4. ESTRATÉGIAS DISPONÍVEIS:
 *
 *    - MappingContextFactory.ownerWithPets(): Owner + Pets (sem appointments)
 *    - MappingContextFactory.petWithOwner(): Pet + Owner (sem pets do owner)
 *    - MappingContextFactory.appointmentFull(): Appointment + Pet + Owner + Employee
 *    - MappingContextFactory.employeeWithProcedures(): Employee + Procedures (sem appointments)
 *    - MappingContextFactory.shallow(): Sem relacionamentos
 *    - MappingContextFactory.full(): Todos os relacionamentos (usar com cuidado)
 *
 * 5. CRIANDO ESTRATÉGIA CUSTOMIZADA:
 *
 *    MappingStrategy customStrategy = new MappingStrategy() {
 *        @Override
 *        public boolean shouldInclude(RelationType relationType) {
 *            return relationType == RelationType.PET_OWNER;
 *        }
 *    };
 *    MappingContext context = MappingContextFactory.custom(customStrategy);
 *
 * BENEFÍCIOS DA NOVA ARQUITETURA:
 * - Thread-safe
 * - Controle granular sobre mapeamentos
 * - Prevenção de recursividade infinita
 * - Compatibilidade com código existente
 * - Performance melhorada
 * - Facilita testes unitários
 * - Segue padrões de mercado (Context Pattern, Strategy Pattern)
 */
public final class MappingGuide {

    private MappingGuide() {
        // Documentation class - prevent instantiation
    }
}
