/*package com.app.quantitymeasurement.model;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementDTO {

    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;
    private double thatValue;
    private String thatUnit;
    private String thatMeasurementType;
    private String operation;
    private double resultValue;
    private String resultUnit;
    private String resultMeasurementType;
    private String resultString;
    private String errorMessage;

    @JsonProperty("error")
    private boolean error;

    public static QuantityMeasurementDTO fromEntity(QuantityMeasurementEntity entity) {
        return new QuantityMeasurementDTO(
            entity.getThisValue(),
            entity.getThisUnit(),
            entity.getThisMeasurementType(),
            entity.getThatValue(),
            entity.getThatUnit(),
            entity.getThatMeasurementType(),
            entity.getOperation(),
            entity.getResultValue(),
            entity.getResultUnit(),
            entity.getResultMeasurementType(),
            entity.getResultString(),
            entity.getErrorMessage(),
            entity.getIsError() != null && entity.getIsError()
        );
    }

    public QuantityMeasurementEntity toEntity() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(this.thisValue);
        entity.setThisUnit(this.thisUnit);
        entity.setThisMeasurementType(this.thisMeasurementType);
        entity.setThatValue(this.thatValue);
        entity.setThatUnit(this.thatUnit);
        entity.setThatMeasurementType(this.thatMeasurementType);
        entity.setOperation(this.operation);
        entity.setResultValue(this.resultValue);
        entity.setResultUnit(this.resultUnit);
        entity.setResultMeasurementType(this.resultMeasurementType);
        entity.setResultString(this.resultString);
        entity.setIsError(this.error);
        entity.setErrorMessage(this.errorMessage);
        return entity;
    }

    public static List<QuantityMeasurementDTO> fromEntityList(List<QuantityMeasurementEntity> entities) {
        return entities.stream()
                .map(QuantityMeasurementDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public static List<QuantityMeasurementEntity> toEntityList(List<QuantityMeasurementDTO> dtos) {
        return dtos.stream()
                .map(QuantityMeasurementDTO::toEntity)
                .collect(Collectors.toList());
    }
}*/









package com.app.quantitymeasurement.model;


import java.util.List;
import java.util.stream.Collectors;

public class QuantityMeasurementDTO {

	private double thisValue;
	private String thisUnit;
	private String thisMeasurementType;

	private double thatValue;
	private String thatUnit;
	private String thatMeasurementType;

	private String operation;

	private String resultString;
	private double resultValue;
	private String resultUnit;
	private String resultMeasurementType;

	private String errorMessage;
	private boolean error;
	
	
	

	// ===== Static Factory Methods =====

	public double getThisValue() {
		return thisValue;
	}

	public void setThisValue(double thisValue) {
		this.thisValue = thisValue;
	}

	public String getThisUnit() {
		return thisUnit;
	}

	public void setThisUnit(String thisUnit) {
		this.thisUnit = thisUnit;
	}

	public String getThisMeasurementType() {
		return thisMeasurementType;
	}

	public void setThisMeasurementType(String thisMeasurementType) {
		this.thisMeasurementType = thisMeasurementType;
	}

	public double getThatValue() {
		return thatValue;
	}

	public void setThatValue(double thatValue) {
		this.thatValue = thatValue;
	}

	public String getThatUnit() {
		return thatUnit;
	}

	public void setThatUnit(String thatUnit) {
		this.thatUnit = thatUnit;
	}

	public String getThatMeasurementType() {
		return thatMeasurementType;
	}

	public void setThatMeasurementType(String thatMeasurementType) {
		this.thatMeasurementType = thatMeasurementType;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	public double getResultValue() {
		return resultValue;
	}

	public void setResultValue(double resultValue) {
		this.resultValue = resultValue;
	}

	public String getResultUnit() {
		return resultUnit;
	}

	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}

	public String getResultMeasurementType() {
		return resultMeasurementType;
	}

	public void setResultMeasurementType(String resultMeasurementType) {
		this.resultMeasurementType = resultMeasurementType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * Convert QuantityMeasurementEntity -> QuantityMeasurementDTO
	 */
	public static QuantityMeasurementDTO fromEntity(QuantityMeasurementEntity entity) {

		QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

		dto.setThisValue(entity.getThisValue());
		dto.setThisUnit(entity.getThisUnit());
		dto.setThisMeasurementType(entity.getThisMeasurementType());

		dto.setThatValue(entity.getThatValue());
		dto.setThatUnit(entity.getThatUnit());
		dto.setThatMeasurementType(entity.getThatMeasurementType());

		dto.setOperation(entity.getOperation());

		dto.setResultString(entity.getResultString());
		dto.setResultValue(entity.getResultValue());
		dto.setResultUnit(entity.getResultUnit());
		dto.setResultMeasurementType(entity.getResultMeasurementType());

		dto.setErrorMessage(entity.getErrorMessage());
		dto.setError(entity.isError());

		return dto;
	}

	/**
	 * Convert QuantityMeasurementDTO -> QuantityMeasurementEntity
	 */
	public QuantityMeasurementEntity toEntity() {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

		entity.setThisValue(this.thisValue);
		entity.setThisUnit(this.thisUnit);
		entity.setThisMeasurementType(this.thisMeasurementType);

		entity.setThatValue(this.thatValue);
		entity.setThatUnit(this.thatUnit);
		entity.setThatMeasurementType(this.thatMeasurementType);

		entity.setOperation(this.operation);

		entity.setResultString(this.resultString);
		entity.setResultValue(this.resultValue);
		entity.setResultUnit(this.resultUnit);
		entity.setResultMeasurementType(this.resultMeasurementType);

		entity.setErrorMessage(this.errorMessage);
		entity.setError(this.error);

		return entity;
	}

	/**
	 * Convert List<QuantityMeasurementEntity> -> List<QuantityMeasurementDTO>
	 */
	public static List<QuantityMeasurementDTO> fromEntityList(List<QuantityMeasurementEntity> entities) {
		return entities.stream().map(QuantityMeasurementDTO::fromEntity).collect(Collectors.toList());
	}

	/**
	 * Convert List<QuantityMeasurementDTO> -> List<QuantityMeasurementEntity>
	 */
	public static List<QuantityMeasurementEntity> toEntityList(List<QuantityMeasurementDTO> dtos) {
		return dtos.stream().map(QuantityMeasurementDTO::toEntity).collect(Collectors.toList());
	}

}
