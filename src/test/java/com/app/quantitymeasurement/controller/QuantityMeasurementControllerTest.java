package com.app.quantitymeasurement.controller;

//import com.app.quantitymeasurement.controller.QuantityMeasurementController;
//import com.app.quantitymeasurement.model.QuantityDTO;
//import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityMeasurementController.class)
class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuantityMeasurementService service;

    @Test
    void testAddQuantities() throws Exception {
        // Prepare mock service response
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setResultValue(2.0);
        dto.setResultUnit("Meter");
        dto.setError(false);

        when(service.add(any())).thenReturn(dto);

        // JSON input with measurementType included
        String jsonInput = """
                {
                  "thisQuantityDTO": {"value": 1.0, "unit": "Meter", "measurementType": "LengthUnit"},
                  "thatQuantityDTO": {"value": 1.0, "unit": "Meter", "measurementType": "LengthUnit"}
                }
                """;

        mockMvc.perform(post("/api/v1/quantities/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultValue").value(2.0))
                .andExpect(jsonPath("$.resultUnit").value("Meter"))
                .andExpect(jsonPath("$.error").value(false));

        verify(service).add(any());
    }

    @Test
    void testDivideQuantitiesError() throws Exception {
        QuantityMeasurementDTO errorDto = new QuantityMeasurementDTO();
        errorDto.setError(true);
        errorDto.setErrorMessage("Divide by zero");

        when(service.divide(any())).thenReturn(errorDto);

        // JSON input with measurementType included
        String jsonInput = """
            {
              "thisQuantityDTO": {"value": 10.0, "unit": "Meter", "measurementType": "LengthUnit"},
              "thatQuantityDTO": {"value": 0.0, "unit": "Meter", "measurementType": "LengthUnit"}
            }
            """;

        mockMvc.perform(post("/api/v1/quantities/divide")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInput))
                .andExpect(status().isOk()) // controller returns 200 with error DTO
                .andExpect(jsonPath("$.error").value(true))
                .andExpect(jsonPath("$.errorMessage").value("Divide by zero"));

        verify(service).divide(any());
    }
}
