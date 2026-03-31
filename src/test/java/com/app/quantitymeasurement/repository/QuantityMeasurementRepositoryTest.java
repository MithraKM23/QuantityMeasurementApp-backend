package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
//import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QuantityMeasurementRepositoryTest {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Test
    void testSaveAndFindByOperation() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("add");
        entity.setError(false);
        entity.setCreatedAt(LocalDateTime.now());
        repository.save(entity);

        List<QuantityMeasurementEntity> results = repository.findByOperation("add");
        assertEquals(1, results.size());
        assertEquals("add", results.get(0).getOperation());
    }

    @Test
    void testFindByCreatedAtAfter() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation("compare");
        entity.setError(false);
        entity.setCreatedAt(LocalDateTime.now().minusDays(1));
        repository.save(entity);

        List<QuantityMeasurementEntity> results = repository.findByCreatedAtAfter(LocalDateTime.now().minusHours(12));
        assertTrue(results.isEmpty()); // created 1 day ago, so not included
    }

    @Test
    void testFindSuccessfulByOperation() {
        QuantityMeasurementEntity success = new QuantityMeasurementEntity();
        success.setOperation("convert");
        success.setError(false);
        repository.save(success);

        QuantityMeasurementEntity error = new QuantityMeasurementEntity();
        error.setOperation("convert");
        error.setError(true);
        repository.save(error);

        List<QuantityMeasurementEntity> results = repository.findSuccessfulByOperation("convert");
        assertEquals(1, results.size());
        assertFalse(results.get(0).isError());
    }

    @Test
    void testCountByOperationAndIsErrorFalse() {
        QuantityMeasurementEntity e1 = new QuantityMeasurementEntity();
        e1.setOperation("divide");
        e1.setError(false);
        repository.save(e1);

        QuantityMeasurementEntity e2 = new QuantityMeasurementEntity();
        e2.setOperation("divide");
        e2.setError(true);
        repository.save(e2);

        long count = repository.countByOperationAndIsErrorFalse("divide");
        assertEquals(1, count);
    }

    @Test
    void testFindByIsErrorTrue() {
        QuantityMeasurementEntity e1 = new QuantityMeasurementEntity();
        e1.setOperation("subtract");
        e1.setError(true);
        repository.save(e1);

        List<QuantityMeasurementEntity> results = repository.findByIsErrorTrue();
        assertEquals(1, results.size());
        assertTrue(results.get(0).isError());
    }
}
