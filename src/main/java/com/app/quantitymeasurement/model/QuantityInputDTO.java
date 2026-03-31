/*package com.app.quantitymeasurement.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class QuantityInputDTO {
	@Valid
	@NotNull( message = "First value cannot be null" )
	private QuantityDTO thisQuantityDTO;
	
	@Valid
	@NotNull(message = "Second value cannot be null")
	private QuantityDTO thatQuantityDTO;
	
	@Valid
	@Schema(nullable=true)
	private QuantityDTO targetQuantityDTO;
	
	
	
}

*/


package com.app.quantitymeasurement.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class QuantityInputDTO {

	@NotNull(message = "thisQuantityDTO cannot be null")
	@Valid
	private QuantityDTO thisQuantityDTO;

	@NotNull(message = "thatQuantityDTO cannot be null")
	@Valid
	private QuantityDTO thatQuantityDTO;

	// Optional target unit for convert / add / subtract operations
	private String targetUnit;

	public QuantityDTO getThisQuantityDTO() {
		return thisQuantityDTO;
	}

	public void setThisQuantityDTO(QuantityDTO thisQuantityDTO) {
		this.thisQuantityDTO = thisQuantityDTO;
	}

	public QuantityDTO getThatQuantityDTO() {
		return thatQuantityDTO;
	}

	public void setThatQuantityDTO(QuantityDTO thatQuantityDTO) {
		this.thatQuantityDTO = thatQuantityDTO;
	}

	public String getTargetUnit() {
		return targetUnit;
	}

	public void setTargetUnit(String targetUnit) {
		this.targetUnit = targetUnit;
	}
	
	

}
