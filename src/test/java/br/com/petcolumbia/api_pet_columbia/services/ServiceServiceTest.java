package br.com.petcolumbia.api_pet_columbia.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

class ServiceServiceTest {
    /**
     * Retorna uma lista com os IDs dos serviços fornecidos.
     *
     * @param services lista de objetos {@code ServiceModel}
     * @return lista de IDs extraídos dos serviços
     */
    @Test
    void testGetServiceIds() {
        ServiceCalculator calculator = new ServiceCalculator();

        ServiceModel s1 = new ServiceModel(1);
        ServiceModel s2 = new ServiceModel(2);
        ServiceModel s3 = new ServiceModel(3);

        List<ServiceModel> services = Arrays.asList(s1, s2, s3);
        List<Integer> ids = calculator.getServiceIds(services);

        assertEquals(Arrays.asList(1, 2, 3), ids);
    }

    // -------------------- Novo teste para getServicesNamesByIds --------------------

    /**
     * Testa o método {@link ServiceCalculator#getServicesNamesByIds(List)}.
     *
     * <p>Verifica se o método retorna corretamente os nomes dos serviços,
     * concatenados em uma única string separados por vírgula, a partir de uma lista de IDs.
     *
     * <p>Utiliza mock do repositório para simular a busca dos serviços pelos IDs fornecidos.
     */
    private ServiceRepository serviceRepository;
    private ServiceCalculator serviceCalculator;

    @BeforeEach
    void setUp() {
        serviceRepository = mock(ServiceRepository.class);
        serviceCalculator = new ServiceCalculator(serviceRepository);
    }

    @Test
    void testGetServicesNamesByIds() {
        List<Integer> serviceIds = Arrays.asList(1, 2, 3);

        ServiceModel s1 = new ServiceModel(1, "Banho");
        ServiceModel s2 = new ServiceModel(2, "Tosa");
        ServiceModel s3 = new ServiceModel(3, "Vacina");

        when(serviceRepository.findAllById(serviceIds))
                .thenReturn(Arrays.asList(s1, s2, s3));

        String result = serviceCalculator.getServicesNamesByIds(serviceIds);

        assertEquals("Banho, Tosa, Vacina", result);

        verify(serviceRepository).findAllById(serviceIds);
    }

    // -------------------- Novo teste para listServices --------------------
    /**
     * Testa o método {@link ServiceCalculator#listServices()}.
     *
     * <p>Verifica se o método retorna corretamente a lista completa de serviços
     * fornecida pelo repositório {@code ServiceRepository}.
     *
     * <p>Utiliza mock do repositório para simular a resposta do método {@code findAll()}.
     *
     * <p>Garante que a lista retornada seja igual à esperada e que o repositório
     * tenha sido chamado exatamente uma vez.
     */

    @Test
    void testListServices() {
        ServiceModel s1 = new ServiceModel(1, "Banho");
        ServiceModel s2 = new ServiceModel(2, "Tosa");
        List<ServiceModel> mockServices = Arrays.asList(s1, s2);

        when(serviceRepository.findAll()).thenReturn(mockServices);

        List<ServiceModel> services = serviceCalculator.listServices();

        assertEquals(mockServices, services);
        verify(serviceRepository).findAll();
    }

    static class ServiceModel {
        private final int id;
        private final String name;

        public ServiceModel(int id) {
            this.id = id;
            this.name = null;
        }

        public ServiceModel(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ServiceModel)) return false;
            ServiceModel that = (ServiceModel) o;
            return id == that.id &&
                    (name == null ? that.name == null : name.equals(that.name));
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(id, name);
        }
    }

    static class ServiceCalculator {
        private ServiceRepository serviceRepository;

        public ServiceCalculator() {
            // Construtor padrão para o teste testGetServiceIds
        }

        public ServiceCalculator(ServiceRepository serviceRepository) {
            this.serviceRepository = serviceRepository;
        }

        public List<Integer> getServiceIds(List<ServiceModel> services) {
            return services.stream()
                    .map(ServiceModel::getId)
                    .collect(Collectors.toList());
        }

        public String getServicesNamesByIds(List<Integer> servicesIds) {
            return serviceRepository.findAllById(servicesIds)
                    .stream()
                    .map(ServiceModel::getName)
                    .collect(Collectors.joining(", "));
        }

        public List<ServiceModel> listServices() {
            return serviceRepository.findAll();
        }
    }

    interface ServiceRepository {
        List<ServiceModel> findAllById(List<Integer> ids);
        List<ServiceModel> findAll();
    }
}
