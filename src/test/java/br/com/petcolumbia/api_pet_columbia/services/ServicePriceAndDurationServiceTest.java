package br.com.petcolumbia.api_pet_columbia.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ServicePriceAndDurationServiceTest {

    private ServiceCalculator serviceCalculator;

    @BeforeEach
    void setUp() {
        serviceCalculator = Mockito.spy(new ServiceCalculator());
    }

    /**
     * Testa o método {@link ServiceCalculator#calculateTotalPrice(List, PetModel)},
     * verificando se a soma dos preços dos serviços está correta com base nas
     * características do pet (tamanho e pelagem), utilizando mocks para simular os retornos esperados.
     * Para os serviços 1, 2 e 3, espera-se o total de R$ 120,00.
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

    /**
     * Testa o método {@link ServiceCalculator#calculateTotalDuration(List, PetModel)},
     * verificando se a soma das durações dos serviços está correta com base nas
     * características do pet (tamanho e pelagem), utilizando mocks para simular os retornos esperados.
     * Para os serviços 1, 2 e 3, espera-se o total de 60 minutos.
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

    static class ServiceInfo {
        private int duration;
        private double price;

        public ServiceInfo(int duration, double price) {
            this.duration = duration;
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public int getDuration() {
            return duration;
        }
    }

    static class ServiceCalculator {
        public Double calculateTotalPrice(List<Integer> servicesIds, PetModel pet) {
            return servicesIds.stream()
                    .mapToDouble(serviceId ->
                            durationAndPriceOfService(serviceId, pet.getSize(), pet.getCoat())
                                    .getPrice())
                    .sum();
        }

        public Integer calculateTotalDuration(List<Integer> servicesIds, PetModel pet) {
            return servicesIds.stream()
                    .mapToInt(serviceId ->
                            durationAndPriceOfService(serviceId, pet.getSize(), pet.getCoat())
                                    .getDuration())
                    .sum();
        }

        public ServiceInfo durationAndPriceOfService(int serviceId, String size, String coat) {
            // implementação real
            return null;
        }
    }

    interface PetModel {
        String getSize();
        String getCoat();
    }
}
