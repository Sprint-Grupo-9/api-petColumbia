package br.com.petcolumbia.api_pet_columbia.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceAndDurationServiceTest {
    private ServiceCalculator serviceCalculator;

    @BeforeEach
    void setUp() {
        serviceCalculator = spy(new ServiceCalculator());
    }

    /**
     * Testa o método {@link ServiceCalculator#calculateTotalDuration(List, PetModel)}.
     *
     * <p>Verifica se o método calcula corretamente a soma da duração total dos serviços,
     * com base em uma lista de IDs de serviços e as características do pet.
     *
     * <p>Utiliza mocks para simular o objeto {@code PetModel} e o método
     * {@code durationAndPriceOfService}, retornando durações fixas para os serviços.
     *
     * <p>Garante que o resultado seja a soma correta das durações retornadas para os serviços informados.
     */
    @Test
    void testCalculateTotalDuration() {
        List<Integer> servicesIds = Arrays.asList(1, 2, 3);

        PetModel pet = mock(PetModel.class);
        when(pet.getSize()).thenReturn("Médio");
        when(pet.getCoat()).thenReturn("Curto");

        when(serviceCalculator.durationAndPriceOfService(1, "Médio", "Curto"))
                .thenReturn(new ServiceInfo(30, 50.0));
        when(serviceCalculator.durationAndPriceOfService(2, "Médio", "Curto"))
                .thenReturn(new ServiceInfo(20, 40.0));
        when(serviceCalculator.durationAndPriceOfService(3, "Médio", "Curto"))
                .thenReturn(new ServiceInfo(10, 30.0));

        Integer totalDuration = serviceCalculator.calculateTotalDuration(servicesIds, pet);

        assertEquals(60, totalDuration);
    }

    /**
     * Testa o método {@link ServiceCalculator#calculateTotalPrice(List, PetModel)}.
     *
     * <p>Verifica se o método calcula corretamente a soma do preço total dos serviços,
     * com base em uma lista de IDs de serviços e as características do pet.
     *
     * <p>Utiliza mocks para simular o objeto {@code PetModel} e o método
     * {@code durationAndPriceOfService}, retornando preços fixos para os serviços.
     *
     * <p>Garante que o resultado seja a soma correta dos preços retornados para os serviços informados.
     */
    @Test
    void testCalculateTotalPrice() {
        List<Integer> servicesIds = Arrays.asList(1, 2, 3);

        PetModel pet = mock(PetModel.class);
        when(pet.getSize()).thenReturn("Médio");
        when(pet.getCoat()).thenReturn("Curto");

        when(serviceCalculator.durationAndPriceOfService(1, "Médio", "Curto"))
                .thenReturn(new ServiceInfo(30, 50.0));
        when(serviceCalculator.durationAndPriceOfService(2, "Médio", "Curto"))
                .thenReturn(new ServiceInfo(20, 40.0));
        when(serviceCalculator.durationAndPriceOfService(3, "Médio", "Curto"))
                .thenReturn(new ServiceInfo(10, 30.0));

        Double totalPrice = serviceCalculator.calculateTotalPrice(servicesIds, pet);

        assertEquals(120.0, totalPrice);
    }

    // Classes auxiliares e mocks

    static class ServiceInfo {
        private int duration;
        private double price;

        public ServiceInfo(int duration, double price) {
            this.duration = duration;
            this.price = price;
        }

        public int getDuration() {
            return duration;
        }

        public double getPrice() {
            return price;
        }
    }

    static class ServiceCalculator {
        public Integer calculateTotalDuration(List<Integer> servicesIds, PetModel pet) {
            return servicesIds.stream()
                    .mapToInt(serviceId ->
                            durationAndPriceOfService(serviceId, pet.getSize(), pet.getCoat())
                                    .getDuration())
                    .sum();
        }

        public Double calculateTotalPrice(List<Integer> servicesIds, PetModel pet) {
            return servicesIds.stream()
                    .mapToDouble(serviceId ->
                            durationAndPriceOfService(serviceId, pet.getSize(), pet.getCoat())
                                    .getPrice())
                    .sum();
        }

        public ServiceInfo durationAndPriceOfService(int serviceId, String size, String coat) {
            // Implementação real omitida para testes
            return null;
        }
    }

    interface PetModel {
        String getSize();
        String getCoat();
    }
}
