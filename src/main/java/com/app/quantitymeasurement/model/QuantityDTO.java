/*package com.app.quantitymeasurement.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.logging.Logger;

interface IMeasurableUnit{
	        String getUnitName();
	        String getMeasurementType();
	        
}
	@Data
	@Schema(description="A quantity with  value and unit")
public class QuantityDTO {
	private static final Logger logger = Logger.getLogger(QuantityDTO.class.getName());
	
	 // Enums implementing IMeasurableUnit
    public enum LengthUnit implements IMeasurableUnit {
    	METER("Meter", "LengthUnit"),
        CENTIMETER("Centimeter", "LengthUnit"),
        KILOMETER("Kilometer", "LengthUnit");

        private final String unitName;
        private final String measurementType;

        LengthUnit(String unitName, String measurementType) {
            this.unitName = unitName;
            this.measurementType = measurementType;
        }

        @Override public String getUnitName() { return unitName; }
        @Override public String getMeasurementType() { return measurementType; }
    }

    public enum VolumeUnit implements IMeasurableUnit {
        LITER("Liter","VolumeUnit"), MILLILITER("Milliliter","VolumeUnit"), GALLON("Gallon", "VolumeUnit");
        private final String unitName;
        private final String measurementType;

        VolumeUnit(String unitName, String measurementType) {
            this.unitName = unitName;
            this.measurementType = measurementType;
        }

        @Override public String getUnitName() { return unitName; }
        @Override public String getMeasurementType() { return measurementType; }
    }

    public enum WeightUnit implements IMeasurableUnit {
        GRAM("Gram","WeightUnit"), KILOGRAM("Kilogram", "WeightUnit"), POUND("Pound","WeightUnit");
        private final String unitName;
        private final String measurementType;

        WeightUnit(String unitName, String measurementType) {
            this.unitName = unitName;
            this.measurementType = measurementType;
        }

        @Override public String getUnitName() { return unitName; }
        @Override public String getMeasurementType() { return measurementType; }
    }

    public enum TemperatureUnit implements IMeasurableUnit {
        CELSIUS("Celsius", "TemperatureUnit"), FAHRENHEIT("Fahrenheit", "TemperatureUnit"), KELVIN("Kelvin", "TemperatureUnit");
        private final String unitName;
        private final String measurementType;

        TemperatureUnit(String unitName, String measurementType) {
            this.unitName = unitName;
            this.measurementType = measurementType;
        }

        @Override public String getUnitName() { return unitName; }
        @Override public String getMeasurementType() { return measurementType; }    }
    
 // Fields with validation
    @NotNull(message = "Value cannot be empty")
    @Schema(example = "1.0")
    public double value;

    @NotNull(message = "Unit cannot be empty")
    @Schema(example = "FEET", allowableValues= {
    		"FEET", "INCHES", "YARDS", "CENTIMETERS",
    		"LITRE", "MILLILITRE", "GALLON",
    		"MILLIGRAM", "GRAM", "KILOGRAM","POUND", "TONNE",
    		"CELSIUS", "FAHRENHEIT"
    })
    public String unit;

    @NotNull(message = "Measurement type cannot be null")
    @Pattern(
        regexp = "LengthUnit|VolumeUnit|WeightUnit|TemperatureUnit",
        message = "Measurement type must be one of: LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit"
    )
    @Schema(example = "LengthUnit", allowableValues= {
    		"LengthUnit", "VolumeUnit", "WeightUnit", "TemperatureUnit"
    })
   public String measurementType;


    public QuantityDTO() {
	}
    
    public QuantityDTO(double value, String unit) {
    	this.value=value;
    	this.unit=unit;
    }

	// Custom constructor (value + unitName + measurementType)
    public QuantityDTO(Double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }
    
    @AssertTrue(message="Unit must be valid for the specified measurement type")

    // Validation method with logger and try/catch
    public boolean isValidUnit() {
    	logger.info("Validating unit: "+unit +" for measurement type: "+measurementType);
        try {
            switch (measurementType) {
                case "LengthUnit":
                    LengthUnit.valueOf(unit);
                    break;
                case "VolumeUnit":
                	VolumeUnit.valueOf(unit);
                    break;
                case "WeightUnit":
                	WeightUnit.valueOf(unit);
                    break;
                case "TemperatureUnit":
                	TemperatureUnit.valueOf(unit);
                    break;
                default:
                    return false;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}

*/


package com.app.quantitymeasurement.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


import java.util.Arrays;
import java.util.List;


public class QuantityDTO {

	@NotNull(message = "Value cannot be null")
	private Double value;

	@NotEmpty(message = "Unit name cannot be empty")
	private String unit;

	@NotEmpty(message = "Measurement type cannot be empty")
	@Pattern(regexp = "LengthUnit|WeightUnit|VolumeUnit|TemperatureUnit", message = "Measurement type must be LengthUnit, WeightUnit, VolumeUnit, or TemperatureUnit")
	private String measurementType;
	
	

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}

	public static List<String> getLengthUnits() {
		return LENGTH_UNITS;
	}

	public static List<String> getWeightUnits() {
		return WEIGHT_UNITS;
	}

	public static List<String> getVolumeUnits() {
		return VOLUME_UNITS;
	}

	public static List<String> getTemperatureUnits() {
		return TEMPERATURE_UNITS;
	}

	// ===== Valid unit names per measurement type =====
	private static final List<String> LENGTH_UNITS = Arrays.asList("FEET", "INCHES", "YARDS", "CENTIMETERS");

	private static final List<String> WEIGHT_UNITS = Arrays.asList("MILLIGRAM", "GRAM", "KILOGRAM", "POUND", "TONNE");

	private static final List<String> VOLUME_UNITS = Arrays.asList("LITRE", "MILLILITRE", "GALLON");

	private static final List<String> TEMPERATURE_UNITS = Arrays.asList("CELSIUS", "FAHRENHEIT", "KELVIN");

	@AssertTrue(message = "Unit must be valid for the specified measurement type")
	public boolean isUnitValidForMeasurementType() {

		// handled by @NotEmpty and @NotNull
		if (unit == null || measurementType == null)
			return true;

		switch (measurementType) {
		case "LengthUnit":
			return LENGTH_UNITS.contains(unit.toUpperCase());
		case "WeightUnit":
			return WEIGHT_UNITS.contains(unit.toUpperCase());
		case "VolumeUnit":
			return VOLUME_UNITS.contains(unit.toUpperCase());
		case "TemperatureUnit":
			return TEMPERATURE_UNITS.contains(unit.toUpperCase());
		default:
			return false;
		}
	}

}






