/*package com.app.quantitymeasurement.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService{
	private static Logger logger=Logger.getLogger(
			QuantityMeasurementImpl.class.getName()
			);
	
	@Autowired
	private QuantityMeasurementRepository repository;
	
	@Override
    public QuantityMeasurementDTO compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        try {
            boolean equal = thisQuantityDTO.getValue().equals(thatQuantityDTO.getValue());
            dto.setThisValue(thisQuantityDTO.getValue());
            dto.setThisUnit(thisQuantityDTO.getUnitName());
            dto.setThisMeasurementType(thisQuantityDTO.getMeasurementType());
            dto.setThatValue(thatQuantityDTO.getValue());
            dto.setThatUnit(thatQuantityDTO.getUnitName());
            dto.setThatMeasurementType(thatQuantityDTO.getMeasurementType());
            dto.setOperation("COMPARE");
            dto.setResultString(equal ? "Values are equal" : "Values are not equal");
            dto.setIsError(false);
        } catch (Exception e) {
            dto.setIsError(true);
            dto.setErrorMessage("Error during comparison: " + e.getMessage());
        }
        repository.save(dto.toEntity());
        return dto;
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        try {
            // Example conversion logic: Celsius to Fahrenheit
            if ("TemperatureUnit".equals(thisQuantityDTO.getMeasurementType()) &&
                "Celsius".equalsIgnoreCase(thisQuantityDTO.getUnitName()) &&
                "Fahrenheit".equalsIgnoreCase(thatQuantityDTO.getUnitName())) {

                double result = (thisQuantityDTO.getValue() * 9/5) + 32;
                dto.setResultValue(result);
                dto.setResultUnit("Fahrenheit");
                dto.setResultMeasurementType("TemperatureUnit");
                dto.setResultString(thisQuantityDTO.getValue() + " Celsius = " + result + " Fahrenheit");
                dto.setIsError(false);
            } else {
                dto.setIsError(true);
                dto.setErrorMessage("Unsupported conversion");
            }
            dto.setOperation("CONVERT");
        } catch (Exception e) {
            dto.setIsError(true);
            dto.setErrorMessage("Error during conversion: " + e.getMessage());
        }
        repository.save(dto.toEntity());
        return dto;
    }

    @Override
    public QuantityMeasurementDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return add(thisQuantityDTO, thatQuantityDTO, null);
    }

    @Override
    public QuantityMeasurementDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        try {
            double result = thisQuantityDTO.getValue() + thatQuantityDTO.getValue();
            dto.setResultValue(result);
            dto.setOperation("ADD");
            dto.setResultString("Addition result = " + result);
            dto.setIsError(false);
        } catch (Exception e) {
            dto.setIsError(true);
            dto.setErrorMessage("Error during addition: " + e.getMessage());
        }
        repository.save(dto.toEntity());
        return dto;
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return subtract(thisQuantityDTO, thatQuantityDTO, null);
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        try {
            double result = thisQuantityDTO.getValue() - thatQuantityDTO.getValue();
            dto.setResultValue(result);
            dto.setOperation("SUBTRACT");
            dto.setResultString("Subtraction result = " + result);
            dto.setIsError(false);
        } catch (Exception e) {
            dto.setIsError(true);
            dto.setErrorMessage("Error during subtraction: " + e.getMessage());
        }
        repository.save(dto.toEntity());
        return dto;
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        try {
            if (thatQuantityDTO.getValue() != 0) {
                double result = thisQuantityDTO.getValue() / thatQuantityDTO.getValue();
                dto.setResultValue(result);
                dto.setOperation("DIVIDE");
                dto.setResultString("Division result = " + result);
                dto.setIsError(false);
            } else {
                dto.setIsError(true);
                dto.setErrorMessage("Division by zero is not allowed");
            }
        } catch (Exception e) {
            dto.setIsError(true);
            dto.setErrorMessage("Error during division: " + e.getMessage());
        }
        repository.save(dto.toEntity());
        return dto;
    }

    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
        return QuantityMeasurementDTO.fromEntityList(repository.findByOperation(operation));
    }

    @Override
    public List<QuantityMeasurementDTO> getMeasurementsByType(String measurementType) {
        return QuantityMeasurementDTO.fromEntityList(repository.findByThisMeasurementType(measurementType));
    }

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation);
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        return QuantityMeasurementDTO.fromEntityList(repository.findByIsErrorTrue());
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryAfter(LocalDateTime date) {
        return QuantityMeasurementDTO.fromEntityList(repository.findByCreatedAtAfter(date));
    }

}*/



package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.Quantity;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.WeightUnit;
import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
//import com.app.quantitymeasurement.exception.GlobalExceptionHandler;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	@Autowired QuantityMeasurementRepository repository;

	// ===== Convert DTO -> Quantity object =====

	private Quantity<?> convertDtoToModel(QuantityDTO dto) {

		try {
			LengthUnit unit = LengthUnit.valueOf(dto.getUnit());
			return new Quantity<>(dto.getValue(), unit);
		} catch (Exception ignored) {
		}

		try {
			WeightUnit unit = WeightUnit.valueOf(dto.getUnit());
			return new Quantity<>(dto.getValue(), unit);
		} catch (Exception ignored) {
		}

		try {
			VolumeUnit unit = VolumeUnit.valueOf(dto.getUnit());
			return new Quantity<>(dto.getValue(), unit);
		} catch (Exception ignored) {
		}

		try {
			TemperatureUnit unit = TemperatureUnit.valueOf(dto.getUnit());
			return new Quantity<>(dto.getValue(), unit);
		} catch (Exception ignored) {
		}

		throw new QuantityMeasurementException("Unsupported Unit: " + dto.getUnit());
	}

	// ===== Get measurement type name =====

	private String getMeasurementType(Quantity<?> quantity) {
		IMeasurable unit = quantity.getUnit();
		if (unit instanceof LengthUnit)
			return "LengthUnit";
		if (unit instanceof WeightUnit)
			return "WeightUnit";
		if (unit instanceof VolumeUnit)
			return "VolumeUnit";
		if (unit instanceof TemperatureUnit)
			return "TemperatureUnit";
		return "Unknown";
	}

	// ===== Build base DTO from input =====

	private QuantityMeasurementDTO buildBaseDTO(QuantityInputDTO input, Quantity<?> q1, Quantity<?> q2,
			String operation) {
		QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
		dto.setThisValue(input.getThisQuantityDTO().getValue());
		dto.setThisUnit(input.getThisQuantityDTO().getUnit());
		dto.setThisMeasurementType(getMeasurementType(q1));
		dto.setThatValue(input.getThatQuantityDTO().getValue());
		dto.setThatUnit(input.getThatQuantityDTO().getUnit());
		dto.setThatMeasurementType(getMeasurementType(q2));
		dto.setOperation(operation);
		return dto;
	}

	// ===== Save to repository =====

	private void saveToRepository(QuantityMeasurementDTO dto) {
		repository.save(dto.toEntity());
	}

	// ================= COMPARE =================

	@Override
	public QuantityMeasurementDTO compare(QuantityInputDTO input) {

		Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
		Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());

		QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "compare");

		try {
			boolean result = q1.equals(q2);
			dto.setResultString(String.valueOf(result));
			dto.setError(false);
		} catch (Exception e) {
			dto.setErrorMessage(e.getMessage());
			dto.setError(true);
		}

		saveToRepository(dto);
		return dto;
	}

	// ================= CONVERT =================

	@Override
	@SuppressWarnings("unchecked")
	public QuantityMeasurementDTO convert(QuantityInputDTO input) {

		Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
		Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());

		QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "convert");

		try {

			IMeasurable unit = q1.getUnit();

			if (unit instanceof LengthUnit) {
				LengthUnit target = LengthUnit.valueOf(input.getThatQuantityDTO().getUnit());
				Quantity<LengthUnit> result = ((Quantity<LengthUnit>) q1).convertTo(target);
				dto.setResultValue(result.getValue());
				dto.setResultMeasurementType("LengthUnit");

			} else if (unit instanceof WeightUnit) {
				WeightUnit target = WeightUnit.valueOf(input.getThatQuantityDTO().getUnit());
				Quantity<WeightUnit> result = ((Quantity<WeightUnit>) q1).convertTo(target);
				dto.setResultValue(result.getValue());
				dto.setResultMeasurementType("WeightUnit");

			} else if (unit instanceof VolumeUnit) {
				VolumeUnit target = VolumeUnit.valueOf(input.getThatQuantityDTO().getUnit());
				Quantity<VolumeUnit> result = ((Quantity<VolumeUnit>) q1).convertTo(target);
				dto.setResultValue(result.getValue());
				dto.setResultMeasurementType("VolumeUnit");

			} else if (unit instanceof TemperatureUnit) {
				TemperatureUnit target = TemperatureUnit.valueOf(input.getThatQuantityDTO().getUnit());
				Quantity<TemperatureUnit> result = ((Quantity<TemperatureUnit>) q1).convertTo(target);
				dto.setResultValue(result.getValue());
				dto.setResultMeasurementType("TemperatureUnit");
			}

			dto.setError(false);

		} catch (Exception e) {
			dto.setErrorMessage(e.getMessage());
			dto.setError(true);
		}

		saveToRepository(dto);
		return dto;
	}

	// ================= ADD =================

	@Override
	@SuppressWarnings("unchecked")
	public QuantityMeasurementDTO add(QuantityInputDTO input) {

		Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
		Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());

		QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "add");

		try {

			if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
				throw new QuantityMeasurementException(
						"Cannot perform arithmetic between different measurement categories: " + getMeasurementType(q1)
								+ " and " + getMeasurementType(q2));
			}

			Quantity result = ((Quantity) q1).add((Quantity) q2);

			dto.setResultValue(result.getValue());
			dto.setResultUnit(result.getUnit().getUnitName());
			dto.setResultMeasurementType(getMeasurementType(q1));
			dto.setError(false);

		} catch (QuantityMeasurementException e) {
			dto.setErrorMessage("add Error: " + e.getMessage());
			dto.setError(true);
		} catch (Exception e) {
			dto.setErrorMessage("add Error: " + e.getMessage());
			dto.setError(true);
		}

		saveToRepository(dto);
		return dto;
	}

	// ================= SUBTRACT =================

	@Override
	@SuppressWarnings("unchecked")
	public QuantityMeasurementDTO subtract(QuantityInputDTO input) {

		Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
		Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());

		QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "subtract");

		try {

			if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
				throw new QuantityMeasurementException(
						"Cannot perform arithmetic between different measurement categories: " + getMeasurementType(q1)
								+ " and " + getMeasurementType(q2));
			}

			Quantity result = ((Quantity) q1).subtract((Quantity) q2);

			dto.setResultValue(result.getValue());
			dto.setResultUnit(result.getUnit().getUnitName());
			dto.setResultMeasurementType(getMeasurementType(q1));
			dto.setError(false);

		} catch (QuantityMeasurementException e) {
			dto.setErrorMessage("subtract Error: " + e.getMessage());
			dto.setError(true);
		} catch (Exception e) {
			dto.setErrorMessage("subtract Error: " + e.getMessage());
			dto.setError(true);
		}

		saveToRepository(dto);
		return dto;
	}

	// ================= DIVIDE =================

	@Override
	@SuppressWarnings("unchecked")
	public QuantityMeasurementDTO divide(QuantityInputDTO input) {

		Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
		Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());

		QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "divide");

		try {

			if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
				throw new QuantityMeasurementException(
						"Cannot perform arithmetic between different measurement categories: " + getMeasurementType(q1)
								+ " and " + getMeasurementType(q2));
			}

			double result = ((Quantity) q1).divide((Quantity) q2);

			dto.setResultValue(result);
			dto.setResultMeasurementType(getMeasurementType(q1));
			dto.setError(false);

		} catch (QuantityMeasurementException e) {
			dto.setErrorMessage("Divide by zero");
			dto.setError(true);
		} catch (ArithmeticException e) {
			dto.setErrorMessage("Divide by zero");
			dto.setError(true);
		} catch (Exception e) {
			dto.setErrorMessage("divide Error: " + e.getMessage());
			dto.setError(true);
		}

		saveToRepository(dto);
		return dto;
	}

	// ================= HISTORY & REPORTING =================

	@Override
	public List<QuantityMeasurementDTO> getHistoryByOperation(String operation) {
		List<QuantityMeasurementEntity> entities = repository.findByOperation(operation.toLowerCase());
		return QuantityMeasurementDTO.fromEntityList(entities);
	}

	@Override
	public List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType) {
		List<QuantityMeasurementEntity> entities = repository.findByThisMeasurementType(measurementType);
		return QuantityMeasurementDTO.fromEntityList(entities);
	}

	@Override
	public long getOperationCount(String operation) {
		return repository.countByOperationAndIsErrorFalse(operation.toLowerCase());
	}

	@Override
	public List<QuantityMeasurementDTO> getErrorHistory() {
		List<QuantityMeasurementEntity> entities = repository.findByIsErrorTrue();
		return QuantityMeasurementDTO.fromEntityList(entities);
	}

}
