package com.app.quantitymeasurement.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuantityMeasurementServiceImplTest {

    private QuantityMeasurementRepository repository;
    private QuantityMeasurementServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(QuantityMeasurementRepository.class);
        service = new QuantityMeasurementServiceImpl();
        // inject mock repo
        service.repository = repository;
    }

    @Test
    void testAddSameUnits() {
        QuantityDTO q1 = new QuantityDTO();
        QuantityDTO q2 = new QuantityDTO();
        QuantityInputDTO input = new QuantityInputDTO();

        QuantityMeasurementDTO result = service.add(input);

        assertFalse(result.isError());
        assertEquals("LengthUnit", result.getResultMeasurementType());
        verify(repository).save(any()); // check repo interaction
    }

    @Test
    void testAddDifferentUnitsThrowsError() {
        QuantityDTO q1 = new QuantityDTO();
        QuantityDTO q2 = new QuantityDTO();
        QuantityInputDTO input = new QuantityInputDTO();

        QuantityMeasurementDTO result = service.add(input);

        assertTrue(result.isError());
        assertTrue(result.getErrorMessage().contains("Cannot perform arithmetic"));
        verify(repository).save(any());
    }

    @Test
    void testDivideByZero() {
        QuantityDTO q1 = new QuantityDTO();
        QuantityDTO q2 = new QuantityDTO();
        QuantityInputDTO input = new QuantityInputDTO();

        QuantityMeasurementDTO result = service.divide(input);

        assertTrue(result.isError());
        assertEquals("Divide by zero", result.getErrorMessage());
        verify(repository).save(any());
    }
}
