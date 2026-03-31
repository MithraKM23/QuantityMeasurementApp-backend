/*package com.app.quantitymeasurement.service;

import java.time.LocalDateTime;
import java.util.List;

import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;

public interface IQuantityMeasurementService {
	public QuantityMeasurementDTO compare(
			QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO
		);
	
	public QuantityMeasurementDTO convert(
			QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO
			);
	
	public QuantityMeasurementDTO add(
			QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO
			);
	
	public QuantityMeasurementDTO add(
			QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO,
			QuantityDTO targetUnitDTO
			);
	
	public QuantityMeasurementDTO subtract(
			QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO
			);
	
	public QuantityMeasurementDTO subtract(
			QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO,
			QuantityDTO targetUnitDTO
			);
	
	public QuantityMeasurementDTO divide(
			QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO
			);
	
	List<QuantityMeasurementDTO> getOperationHistory(String operation);
	
	// Retrieve history by measurement type
    List<QuantityMeasurementDTO> getMeasurementsByType(String measurementType);
    
    long getOperationCount(String operation);
    
    List<QuantityMeasurementDTO> getErrorHistory();

    // Retrieve history created after a given date
    List<QuantityMeasurementDTO> getHistoryAfter(LocalDateTime date);

}*/

package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;

import java.util.List;

public interface IQuantityMeasurementService {

    // ===== Core Operations =====

    QuantityMeasurementDTO compare(QuantityInputDTO input);

    QuantityMeasurementDTO convert(QuantityInputDTO input);

    QuantityMeasurementDTO add(QuantityInputDTO input);

    QuantityMeasurementDTO subtract(QuantityInputDTO input);

    QuantityMeasurementDTO divide(QuantityInputDTO input);

    // ===== History & Reporting =====

    // Get history of all measurements by operation type
    List<QuantityMeasurementDTO> getHistoryByOperation(String operation);

    // Get history of all measurements by measurement type
    List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType);

    // Get count of successful operations by operation type
    long getOperationCount(String operation);

    // Get all errored measurements
    List<QuantityMeasurementDTO> getErrorHistory();

}
