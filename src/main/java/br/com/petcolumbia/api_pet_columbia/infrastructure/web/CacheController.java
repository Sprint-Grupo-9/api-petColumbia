package br.com.petcolumbia.api_pet_columbia.infrastructure.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final CacheManager cacheManager;

    public CacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @DeleteMapping("/clear")
    @Operation(summary = "Limpa todos os caches", description = "Remove todos os dados armazenados em cache")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> clearAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            var cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        });
        return ResponseEntity.ok("Todos os caches foram limpos com sucesso");
    }

    @DeleteMapping("/clear/{cacheName}")
    @Operation(summary = "Limpa um cache específico", description = "Remove os dados de um cache específico")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> clearCache(@PathVariable String cacheName) {
        var cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
            return ResponseEntity.ok("Cache '" + cacheName + "' foi limpo com sucesso");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/names")
    @Operation(summary = "Lista todos os nomes dos caches", description = "Retorna os nomes de todos os caches configurados")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Object> getCacheNames() {
        return ResponseEntity.ok(cacheManager.getCacheNames());
    }
}
