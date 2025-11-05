package br.com.petcolumbia.api_pet_columbia.infrastructure.dto.mappers.entity_mapper.common;

import java.util.HashSet;
import java.util.Set;

/**
 * Context for controlling mapping operations and preventing infinite recursion.
 * This class is thread-safe and should be used within the scope of a single mapping operation.
 */
public final class MappingContext {

    private final Set<String> processedEntities = new HashSet<>();
    private final MappingStrategy strategy;

    public MappingContext(MappingStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean shouldMapRelation(String entityKey, RelationType relationType) {
        if (processedEntities.contains(entityKey)) {
            return false; // Prevent circular references
        }
        return strategy.shouldInclude(relationType);
    }

    public boolean isBeingProcessed(String entityKey) {
        return processedEntities.contains(entityKey);
    }

    public boolean shouldIncludeRelation(RelationType relationType) {
        return strategy.shouldInclude(relationType);
    }

    public void markAsProcessed(String entityKey) {
        processedEntities.add(entityKey);
    }

    public void unmarkAsProcessed(String entityKey) {
        processedEntities.remove(entityKey);
    }

    public MappingStrategy getStrategy() {
        return strategy;
    }

    /**
     * Creates a key for tracking processed entities
     */
    public static String createEntityKey(String entityType, Integer id) {
        return entityType + ":" + id;
    }
}
